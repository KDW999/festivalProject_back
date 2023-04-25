package com.festival.back.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.request.board.RecommendRequestDto;
import com.festival.back.dto.request.board.PostReviewBoardRequestDto;
import com.festival.back.dto.request.board.RecommendRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.board.RecommendResponseDto;
import com.festival.back.dto.response.board.PostCommentResponseDto;
import com.festival.back.dto.response.board.PostFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.board.RecommendResponseDto;
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


    @Override
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
             System.out.println(boardEntity);
             data = new PostFestivalReviewBoardResponseDto(boardEntity,festivalEntity);

            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);

        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

  
    
    
}
