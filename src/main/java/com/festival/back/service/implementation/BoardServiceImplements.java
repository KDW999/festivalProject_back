package com.festival.back.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.dto.request.PostReviewBoardRequestDto;
import com.festival.back.dto.response.PostFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.entity.BoardEntity;
import com.festival.back.entity.FestivalEntity;
import com.festival.back.entity.UserEntity;
import com.festival.back.repository.BoardRepository;
import com.festival.back.repository.FestivalRepository;
import com.festival.back.repository.UserRepository;
import com.festival.back.service.BoardService;

@Service
public class BoardServiceImplements implements BoardService {
    @Autowired BoardRepository boardRepository;
    @Autowired UserRepository userRepository;
    @Autowired FestivalRepository festivalRepository;

    public ResponseDto<PostFestivalReviewBoardResponseDto> postReviewBoard(PostReviewBoardRequestDto dto,String userId) {
        PostFestivalReviewBoardResponseDto data = null;
         int festivalNumber=dto.getFestivalNumber();

        try {
            UserEntity userEntity =userRepository.FindByuserId(userId);
            if(userEntity == null){
                return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);
            }
            FestivalEntity festivalEntity=festivalRepository.FindByFestivalNumber(festivalNumber);
            if(festivalEntity == null){
            return ResponseDto.setFail(ResponseMessage.NOt_EXIST_FESTIVAL_NUMBER);
            }

            BoardEntity boardEntity = new BoardEntity(userEntity, dto, festivalEntity);
            System.out.println(boardEntity.toString());
            boardRepository.save(boardEntity);
            data=new PostFestivalReviewBoardResponseDto(boardEntity);

            
        } catch (Exception e) {
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS,  data);

    }
    
}
