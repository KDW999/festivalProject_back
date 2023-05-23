package com.festival.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.dto.request.board.PatchCommentRequestDto;
import com.festival.back.dto.request.board.PatchReviewBoardRequestDto;
import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.request.board.PostReviewBoardRequestDto;
import com.festival.back.dto.request.board.RecommendReviewBoardRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.board.DeleteCommentResponseDto;
import com.festival.back.dto.response.board.DeleteReviewBoardResponseDto;
import com.festival.back.dto.response.board.GetReviewBoardResponseDto;
import com.festival.back.dto.response.board.GetInterestedFestivalListResponseDto;
import com.festival.back.dto.response.board.GetMyReviewBoardListResponseDto;
import com.festival.back.dto.response.board.GetOneReviewBoardListResponseDto;
import com.festival.back.dto.response.board.GetAllReviewBoardListResponseDto;
import com.festival.back.dto.response.board.GetSearchReviewBoardListResponseDto;
import com.festival.back.dto.response.board.PatchCommentResponseDto;
import com.festival.back.dto.response.board.PatchReviewBoardResponseDto;
import com.festival.back.dto.response.board.PostCommentResponseDto;
import com.festival.back.dto.response.board.RecommendReviewBoardResponseDto;
import com.festival.back.dto.response.board.PostReviewBoardResponseDto;
import com.festival.back.entity.BoardEntity;
import com.festival.back.entity.CommentEntity;
import com.festival.back.entity.FestivalEntity;
import com.festival.back.entity.InterestedFestivalEntity;
import com.festival.back.entity.RecommendEntity;
import com.festival.back.entity.SearchwordLogEntity;
import com.festival.back.entity.UserEntity;
import com.festival.back.repository.BoardRepository;
import com.festival.back.repository.CommentRepository;
import com.festival.back.repository.FestivalRepository;
import com.festival.back.repository.InterestedFestivalRepository;
import com.festival.back.repository.RecommendRepository;
import com.festival.back.repository.SearchWordLogRepository;
import com.festival.back.repository.UserRepository;
import com.festival.back.service.BoardService;

@Service
public class BoardServiceImplements implements BoardService {

    @Autowired private BoardRepository boardRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private RecommendRepository recommendRepository;
    @Autowired private FestivalRepository festivalRepository;
    @Autowired private InterestedFestivalRepository interestedFestivalRepository;
    @Autowired private SearchWordLogRepository searchWordLogRepository;
    
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

            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);

            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<RecommendEntity> recommendList = recommendRepository.findByBoardNumber(boardNumber);

            data = new PostCommentResponseDto(boardEntity, commentList, recommendList);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 댓글 수정 기능
    public ResponseDto<PatchCommentResponseDto> patchComment(String userId, PatchCommentRequestDto dto) {

        PatchCommentResponseDto data = null;

        int boardNumber = dto.getBoardNumber();
        int commentNumber = dto.getCommentNumber();

        try{
            CommentEntity commentEntity = commentRepository.findByCommentNumber(commentNumber);
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber); 
            
            if(boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            if(boardEntity.getBoardNumber() != commentEntity.getBoardNumber()) return ResponseDto.setFail(ResponseMessage.NOT_EXITST_FESTIVAL_NAME);

            boolean isEqualWriter = userId.equals(commentEntity.getWriterUserId());
            if(!isEqualWriter) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            commentEntity.patch(dto);
            commentRepository.save(commentEntity);

            List<RecommendEntity> recommendList = recommendRepository.findByBoardNumber(boardNumber);
            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);

            data = new PatchCommentResponseDto(boardEntity, recommendList, commentList); 

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        } 
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
    //? 추천 기능
    public ResponseDto<RecommendReviewBoardResponseDto> recommend(String userId, RecommendReviewBoardRequestDto dto) {

        RecommendReviewBoardResponseDto data = null;
        int boardNumber = dto.getBoardNumber();

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            RecommendEntity recommendEntity = recommendRepository.findByUserIdAndBoardNumber(userId, boardNumber); 
            if(recommendEntity == null) {
                recommendEntity = new RecommendEntity(userEntity, boardNumber);
                recommendRepository.save(recommendEntity);
                boardEntity.increaseRecommendCount();
            } else {
                recommendRepository.delete(recommendEntity);
                boardEntity.decreaseRecommendCount();
            }

            boardRepository.save(boardEntity);

            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<RecommendEntity> recommendList = recommendRepository.findByBoardNumber(boardNumber);

            data = new RecommendReviewBoardResponseDto(boardEntity, recommendList, commentList);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 축제 후기 게시글 작성 -김종빈
    public ResponseDto<PostReviewBoardResponseDto> postReviewBoard(String userId,PostReviewBoardRequestDto dto) {
        
        PostReviewBoardResponseDto data = null;

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            BoardEntity boardEntity = new BoardEntity(userEntity,dto);
            boardRepository.save(boardEntity);
            data = new PostReviewBoardResponseDto(boardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // ? 특정 축제 후기 게시글 불러오기-김종빈
    public ResponseDto<GetReviewBoardResponseDto> getReviewBoard( Integer boardNumber) {

        GetReviewBoardResponseDto data = null;

        try {
            if(boardNumber == null) return ResponseDto.setFail(ResponseMessage.VAILDATION_FAILD);

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);
            
            List<RecommendEntity> recommdList = recommendRepository.findByBoardNumber(boardNumber);
            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);

            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);
            data=new GetReviewBoardResponseDto(boardEntity,recommdList,commentList);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
    //? 댓글 삭제
    public ResponseDto<DeleteCommentResponseDto> deleteComment(String userId, int commentNumber) {
        
        DeleteCommentResponseDto data = null;

        try {
            CommentEntity commentEntity = commentRepository.findByCommentNumber(commentNumber);
            if(commentEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXITST_COMMENT_NUMBER);

            boolean isEqualWriter = userId.equals(commentEntity.getWriterUserId());
            if(!isEqualWriter) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);

            commentRepository.deleteByCommentNumber(commentNumber);
            commentRepository.delete(commentEntity);
            
            int boardNumber = commentEntity.getBoardNumber();
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            boardEntity.decreaseCommentCount();
            boardRepository.save(boardEntity);
            
            data = new DeleteCommentResponseDto(true);
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
    // ? 후기 게시글 수정-김종빈
    public ResponseDto<PatchReviewBoardResponseDto> patchReivewBoard(String userId,PatchReviewBoardRequestDto dto) {
        
        PatchReviewBoardResponseDto data = null;
        int boardNumber = dto.getBoardNumber();

        try {
            BoardEntity boardEntity=boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            boolean isEqulWriter = userId.equals(boardEntity.getWriterUserId());
            if (!isEqulWriter) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);

            List<RecommendEntity> recommdList = recommendRepository.findByBoardNumber(boardNumber);
            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);

            System.out.println("dto" + dto.toString());
            System.out.println(boardEntity.toString());
            boardEntity.patch(dto);
            boardRepository.save(boardEntity);

            data = new PatchReviewBoardResponseDto(boardEntity, commentList, recommdList);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
    // ? 특정 게시물 삭제-김종빈
    public ResponseDto<DeleteReviewBoardResponseDto> deleteBoard(String userId, int boardNumber) {
        
        DeleteReviewBoardResponseDto data = null;

        try {
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            boolean isEqulWriter = userId.equals(boardEntity.getWriterUserId());
            if (!isEqulWriter) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);

            commentRepository.deleteByBoardNumber(boardNumber);
            recommendRepository.deleteByBoardNumber(boardNumber);

            boardRepository.delete(boardEntity);
            data = new DeleteReviewBoardResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // ? 내가 쓴 후기 게시글 불러오기 -김종빈
    public ResponseDto<List<GetMyReviewBoardListResponseDto>> getMyList(String userId){
        
        List<GetMyReviewBoardListResponseDto> data = null;

        try {
            List<BoardEntity> boardList = boardRepository.findBywriterUserIdOrderByBoardWriteDatetimeDesc(userId);
            data = GetMyReviewBoardListResponseDto.copyList(boardList);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);  
    }

    // ? 관심있는 축제 타입 리스트 받아오기 - 감재현
    public ResponseDto<List<GetInterestedFestivalListResponseDto>> getInterestedFestivalList(String userId) {
        
        List<GetInterestedFestivalListResponseDto> data = null;

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            List<InterestedFestivalEntity> interestedFestivalList = interestedFestivalRepository.findByUserId(userId);
            if (interestedFestivalList == null || interestedFestivalList.size() == 0) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_INTERESTED_FESTIVAL_TYPE);

            List<String> interestedFestivalTypeList = new ArrayList<>();
            for (InterestedFestivalEntity interestedFestival: interestedFestivalList)
                interestedFestivalTypeList.add(interestedFestival.getInterestedFestivalType());
            List<FestivalEntity> festivalList = festivalRepository.findByFestivalTypeIn(interestedFestivalTypeList);
            if(festivalList.isEmpty()) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_INTERESTED_FESTIVAL_TYPE);

            data = GetInterestedFestivalListResponseDto.copyList(festivalList);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 후기 게시판 검색
    public ResponseDto<List<GetSearchReviewBoardListResponseDto>> getSearchReviewBoardList(String searchWord) {
        
        List<GetSearchReviewBoardListResponseDto> data =null;

        try {
            SearchwordLogEntity searchwordLogEntity = new SearchwordLogEntity(searchWord); 
            searchWordLogRepository.save(searchwordLogEntity);
            List<BoardEntity> boardEntity =
            boardRepository.findByBoardTitleContainsOrBoardContentContainsOrderByBoardWriteDatetimeDesc(searchWord,searchWord);
            if(boardEntity.isEmpty()) return ResponseDto.setFail(ResponseMessage.NO_SEARCH_RESULTS);
            
            data = GetSearchReviewBoardListResponseDto.copyList(boardEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //  ? 특정 축제 전체 후기 리스트 만 반환
    public ResponseDto<List<GetOneReviewBoardListResponseDto>> getOneFestivalReviewBoard(int festivalNumber) {
    
        List<GetOneReviewBoardListResponseDto> data = null;
    
        try {
            List<BoardEntity> boardEntityList = boardRepository.findByFestivalNumberOrderByBoardWriteDatetimeDesc(festivalNumber);
            data = GetOneReviewBoardListResponseDto.copyList(boardEntityList);
            
        } catch (Exception exception) {
            exception.printStackTrace();    
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 전체 축제 반환
    public ResponseDto<List<GetAllReviewBoardListResponseDto>> getAllReviewBoardList() {
        
        List<GetAllReviewBoardListResponseDto> data = null;

        try {
            List<BoardEntity> boardList = boardRepository.findByOrderByBoardWriteDatetimeDesc();
            data= GetAllReviewBoardListResponseDto.copyList(boardList);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}