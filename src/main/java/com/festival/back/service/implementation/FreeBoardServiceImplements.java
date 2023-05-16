package com.festival.back.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.dto.request.freeboard.PostFreeBoardRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.freeboard.PostFreeBoardResponseDto;
import com.festival.back.entity.FreeBoardEntity;
import com.festival.back.entity.UserEntity;
import com.festival.back.repository.FreeBoardCommentRepository;
import com.festival.back.repository.FreeBoardRecommendRepository;
import com.festival.back.repository.FreeBoardRepository;
import com.festival.back.repository.UserRepository;
import com.festival.back.service.FreeBoardService;

@Service
public class FreeBoardServiceImplements implements FreeBoardService {
    
    @Autowired private UserRepository userRepository;
    @Autowired private FreeBoardRepository freeBoardRepository;
    @Autowired private FreeBoardCommentRepository freeBoardCommentRepository;
    @Autowired private FreeBoardRecommendRepository freeBoardRecommendRepository;

    public ResponseDto<PostFreeBoardResponseDto> postFreeBoard(String userId, PostFreeBoardRequestDto dto) {
        PostFreeBoardResponseDto data = null;
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            FreeBoardEntity freeBoardEntity = new FreeBoardEntity(userEntity, dto);
            freeBoardRepository.save(freeBoardEntity);
            data = new PostFreeBoardResponseDto(freeBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
