package com.festival.back.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.dto.request.freeboard.PatchFreeBoardRequestDto;
import com.festival.back.dto.request.freeboard.PostFreeBoardRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.freeboard.DeleteFreeBoardResponseDto;
import com.festival.back.dto.response.freeboard.PatchFreeBoardResponseDto;
import com.festival.back.dto.response.freeboard.PostFreeBoardResponseDto;
import com.festival.back.entity.FreeBoardCommentEntity;
import com.festival.back.entity.FreeBoardEntity;
import com.festival.back.entity.FreeBoardRecommendEntity;
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

    public ResponseDto<PatchFreeBoardResponseDto> patchFreeBoard(String userId, PatchFreeBoardRequestDto dto) {
        PatchFreeBoardResponseDto data = null;
        int freeBoardNumber = dto.getFreeBoardNumber();

        try {
            FreeBoardEntity freeBoardEntity = freeBoardRepository.findByFreeBoardNumber(freeBoardNumber);
            if(freeBoardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            boolean isEqualWriter = freeBoardEntity.getWriterUserId().equals(userId);
            if (!isEqualWriter) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);

            List<FreeBoardCommentEntity> commentList = freeBoardCommentRepository.findByFreeBoardNumberOrderByWriteDatetimeDesc(freeBoardNumber);
            List<FreeBoardRecommendEntity> recommendList = freeBoardRecommendRepository.findByFreeBoardNumber(freeBoardNumber);

            freeBoardEntity.patch(dto);
            freeBoardRepository.save(freeBoardEntity);

            data = new PatchFreeBoardResponseDto(freeBoardEntity, commentList, recommendList);

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<DeleteFreeBoardResponseDto> deleteFreeBoard(String userId, int freeBoardNumber) {
        DeleteFreeBoardResponseDto data = null;

        try {
            FreeBoardEntity freeBoardEntity = freeBoardRepository.findByFreeBoardNumber(freeBoardNumber);
            if (freeBoardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            boolean isEqualWriter = freeBoardEntity.getWriterUserId().equals(userId);
            if (!isEqualWriter) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);

            freeBoardCommentRepository.deleteByFreeBoardNumber(freeBoardNumber);
            freeBoardRecommendRepository.deleteByFreeBoardNumber(freeBoardNumber);

            freeBoardRepository.delete(freeBoardEntity);
            data = new DeleteFreeBoardResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
