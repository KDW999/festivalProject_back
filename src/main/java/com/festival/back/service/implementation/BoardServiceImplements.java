package com.festival.back.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.dto.request.board.PatchCommentRequestDto;
import com.festival.back.dto.request.board.PatchReviewBoardRequestDto;
import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.request.board.PostReviewBoardRequestDto;
import com.festival.back.dto.request.board.RecommendRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.board.PatchCommentResponseDto;
import com.festival.back.dto.response.board.PatchFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.board.PostCommentResponseDto;
import com.festival.back.dto.response.board.RecommendResponseDto;
import com.festival.back.dto.response.board.DeleteCommentResponseDto;
import com.festival.back.dto.response.board.DeleteFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.board.GetFestivalReviewBoardListResponseDto;
import com.festival.back.dto.response.board.GetFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.board.GetInterestedFestivalListResponseDto;
import com.festival.back.dto.response.board.GetMyFestivalReviewBoardListResponseDto;
import com.festival.back.dto.response.board.PostFestivalReviewBoardResponseDto;
import com.festival.back.entity.BoardEntity;
import com.festival.back.entity.CommentEntity;
import com.festival.back.entity.FestivalEntity;
import com.festival.back.entity.RecommendEntity;
import com.festival.back.entity.UserEntity;
import com.festival.back.repository.BoardRepository;
import com.festival.back.repository.CommentRepository;
import com.festival.back.repository.FestivalRepository;
import com.festival.back.repository.RecommendRepository;
import com.festival.back.repository.UserRepository;
import com.festival.back.service.BoardService;

@Service
public class BoardServiceImplements implements BoardService {

    @Autowired private BoardRepository boardRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private RecommendRepository recommendRepository;
    @Autowired private FestivalRepository festivalRepository;
    
    //? 댓글 작성
    public ResponseDto<PostCommentResponseDto> postComment(String userId, PostCommentRequestDto dto) {
        PostCommentResponseDto data = null;

        int boardNumber = dto.getBoardNumber();

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);
            
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            CommentEntity commentEntity = new CommentEntity(userEntity, dto);
            commentRepository.save(commentEntity);

            boardEntity.increaseRecommendCount();
            boardRepository.save(boardEntity);

            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<RecommendEntity> RecommendList = recommendRepository.findByBoardNumber(boardNumber); 

            data = new PostCommentResponseDto(boardEntity, commentList, RecommendList);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<PatchCommentResponseDto> patchComment(String userId, PatchCommentRequestDto dto){

        PatchCommentResponseDto data = null;

        int boardNumber = dto.getBoardNumber();
        int commentNumber = dto.getCommentNumber();
        // int commentNumber = dto.getCommentNumber();

        try{
            
            CommentEntity commentEntity = commentRepository.findByCommentNumber(commentNumber);
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber); 
            
            if(boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            // if(commentNumber == null) return ResponseDto.setFail(ResponseMessage.NOT_EXITST_COMMENT_NUMBER);

            boolean isEqualWriter = userId.equals(commentEntity.getWriterId());
            if(!isEqualWriter) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            commentEntity.patch(dto);
            commentRepository.save(commentEntity);

            List<RecommendEntity> recommendList = recommendRepository.findByBoardNumber(boardNumber);
            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);

            data = new PatchCommentResponseDto(boardEntity, recommendList, commentList); 

        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        } 
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    //? 추천 기능
    public ResponseDto<RecommendResponseDto> recommend(String id, RecommendRequestDto dto) {

        RecommendResponseDto data = null;
        int boardNumber = dto.getBoardNumber(); //? Request에서 내가 입력한 게시물 번호

        try {

            //? userEntity에 id값 삽입
            UserEntity userEntity = userRepository.findByUserId(id); //? findById는 기본 메서드라 안됌
            if(userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            RecommendEntity recommendEntity = recommendRepository.findByUserIdAndBoardNumber(id, boardNumber);
            if(recommendEntity == null){ //? 안누른 사람이 누르면 증가
                recommendEntity = new RecommendEntity(userEntity, boardNumber);
                recommendRepository.save(recommendEntity); //? 눌렀으니 누른 사람의 userId, boardNumber 저장
                boardEntity.increaseRecommendCount();

            }

            else {
                recommendRepository.delete(recommendEntity);
                boardEntity.decreaseRecommendCount();
            }

            boardRepository.save(boardEntity);

            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<RecommendEntity> recommendList = recommendRepository.findByBoardNumber(boardNumber);

            data = new RecommendResponseDto(boardEntity, recommendList, commentList);
            

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

//   ? 축제 후기 게시글 작성 -김종빈
    public ResponseDto<PostFestivalReviewBoardResponseDto> postFestivalReviewBoard(String userId,PostReviewBoardRequestDto dto) {
        PostFestivalReviewBoardResponseDto data = null;
        int festivalNumber=dto.getFestivalNumber();

        try {
            UserEntity userEntity =userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            FestivalEntity festivalEntity=festivalRepository.findByFestivalNumber(festivalNumber);
            if(festivalEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_FESTIVAL_NUMBER);

            BoardEntity boardEntity =new BoardEntity(userEntity,dto);
            boardRepository.save(boardEntity);
            data = new PostFestivalReviewBoardResponseDto(boardEntity,festivalEntity);

            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);

        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // ? 특정 축제 후기 게시글 불러오기-김종빈
    public ResponseDto<GetFestivalReviewBoardResponseDto> getFestivalReviewBoard(int festivalNumber,Integer boardNumber) {
        GetFestivalReviewBoardResponseDto data= null;
        // int boardNumber=dto.getBoardNumber();
        // int festivalNumber=dto.getFestivalNumber();  

        try {
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);
            List<RecommendEntity> recommdList=recommendRepository.findByBoardNumber(boardNumber);
            List<CommentEntity> commentList=commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            FestivalEntity festivalEntity =festivalRepository.findByFestivalNumber(festivalNumber);
            if(festivalEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_FESTIVAL_NUMBER);

            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);
            data=new GetFestivalReviewBoardResponseDto(boardEntity, recommdList, commentList, festivalEntity);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

   
    
    // ? 특정축제 전체 후기 게시글 불러오기 -김종빈
    public ResponseDto<List<GetFestivalReviewBoardListResponseDto>> getFestivalReviewBoardList(Integer festivalNumber) {
        List<GetFestivalReviewBoardListResponseDto> data = null;

        try {
            List<BoardEntity> boardEntityList=boardRepository.findByFestivalNumberOrderByBoardWriteDatetimeDesc(festivalNumber);
            if(boardEntityList.isEmpty()) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);
    
        
       
            data=GetFestivalReviewBoardListResponseDto.copyList(boardEntityList);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

// ? 후기 게시글 수정-김종빈
    public ResponseDto<PatchFestivalReviewBoardResponseDto> patchReivewBoard(String userId,PatchReviewBoardRequestDto dto) {
        PatchFestivalReviewBoardResponseDto data = null;
        int festivalNumber=dto.getFestivalNumber();
        int boardNumber=dto.getBoardNumber();

        try {
            BoardEntity boardEntity=boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            boolean isEqulWriter = userId.equals(boardEntity.getWriterId());
            if (!isEqulWriter) return ResponseDto.setFail(ResponseMessage.NOT_PORMISSION);

            List<RecommendEntity> recommdList=recommendRepository.findByBoardNumber(boardNumber);
            List<CommentEntity> commentList=commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);

            FestivalEntity festivalEntity =festivalRepository.findByFestivalNumber(festivalNumber);
            if(festivalEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_FESTIVAL_NUMBER);
           System.out.println("dto"+dto.toString());
           System.out.println(boardEntity.toString());
            boardEntity.patch(dto);
            boardRepository.save(boardEntity);

            data = new PatchFestivalReviewBoardResponseDto(boardEntity, festivalEntity, commentList, recommdList);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
   
    // ? 특정 게시물 삭제-김종빈
    public ResponseDto<DeleteFestivalReviewBoardResponseDto> deleteBoard(String userId, int boardNumber) {
        DeleteFestivalReviewBoardResponseDto data = null;

        try {
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            boolean isEqulWriter = userId.equals(boardEntity.getWriterId());
            if (!isEqulWriter) return ResponseDto.setFail(ResponseMessage.NOT_PORMISSION);

                commentRepository.deleteByBoardNumber(boardNumber);
                recommendRepository.deleteByBoardNumber(boardNumber);

            boardRepository.delete(boardEntity);
            data = new DeleteFestivalReviewBoardResponseDto(true);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // ? 내가 쓴 후기 게시글 불러오기 -김종빈
    public ResponseDto<List<GetMyFestivalReviewBoardListResponseDto>> getMyList(String userId){
        List<GetMyFestivalReviewBoardListResponseDto> data = null;

        try {

            List<BoardEntity> boardList = boardRepository.findBywriterIdOrderByBoardWriteDatetimeDesc(userId);
            data = GetMyFestivalReviewBoardListResponseDto.copyList(boardList);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        
    }

    // ? 
    public ResponseDto<List<GetInterestedFestivalListResponseDto>> GetInterestedFestivalList(String userId) {
        List<GetInterestedFestivalListResponseDto> data = null;

        try {
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        
    }


}

