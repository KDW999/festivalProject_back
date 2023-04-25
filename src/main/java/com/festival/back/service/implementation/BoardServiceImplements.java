package com.festival.back.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.request.board.PostReviewBoardRequestDto;
import com.festival.back.dto.request.board.RecommendRequestDto;
import com.festival.back.dto.response.ResponseDto;
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
    @Autowired BoardRepository boardRepository;
    @Autowired UserRepository userRepository;
    @Autowired FestivalRepository festivalRepository;

    @Autowired private CommentRepository commentRepository;
    @Autowired private RecommendRepository recommendRepository;
    


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
