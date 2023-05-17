package com.festival.back.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.dto.request.freeboard.FreeBoardRecommendRequestDto;
import com.festival.back.dto.request.freeboard.PatchFreeBoardCommentRequestDto;
import com.festival.back.dto.request.freeboard.PatchFreeBoardRequestDto;
import com.festival.back.dto.request.freeboard.PostFreeBoardCommentRequestDto;
import com.festival.back.dto.request.freeboard.PostFreeBoardRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.freeboard.DeleteFreeBoardCommentResponseDto;
import com.festival.back.dto.response.freeboard.DeleteFreeBoardResponseDto;
import com.festival.back.dto.response.freeboard.FreeBoardRecommendResponseDto;
import com.festival.back.dto.response.freeboard.PatchFreeBoardCommentResponseDto;
import com.festival.back.dto.response.freeboard.PatchFreeBoardResponseDto;
import com.festival.back.dto.response.freeboard.PostFreeBoardCommentResponseDto;
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

    public ResponseDto<PostFreeBoardCommentResponseDto> postFreeBoardComment(String userId, PostFreeBoardCommentRequestDto dto) {
        PostFreeBoardCommentResponseDto data = null;
        int freeBoardNumber = dto.getFreeBoardNumber();

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            FreeBoardEntity freeBoardEntity = freeBoardRepository.findByFreeBoardNumber(freeBoardNumber);
            if (freeBoardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            FreeBoardCommentEntity freeBoardCommentEntity = new FreeBoardCommentEntity(userEntity, dto);
            freeBoardCommentRepository.save(freeBoardCommentEntity);

            freeBoardEntity.increaseCommentCount();
            freeBoardRepository.save(freeBoardEntity);

            List<FreeBoardCommentEntity> commentList = freeBoardCommentRepository.findByFreeBoardNumberOrderByWriteDatetimeDesc(freeBoardNumber);
            List<FreeBoardRecommendEntity> recommendList = freeBoardRecommendRepository.findByFreeBoardNumber(freeBoardNumber);

            data = new PostFreeBoardCommentResponseDto(freeBoardEntity, commentList, recommendList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<PatchFreeBoardCommentResponseDto> patchFreeBoardComment (String userId, PatchFreeBoardCommentRequestDto dto) {
        PatchFreeBoardCommentResponseDto data = null;
        int freeBoardNumber = dto.getFreeBoardNumber();
        int freeBoardCommentNumber = dto.getFreeBoardCommentNumber();

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            FreeBoardEntity freeBoardEntity = freeBoardRepository.findByFreeBoardNumber(freeBoardNumber);
            if (freeBoardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            FreeBoardCommentEntity freeBoardCommentEntity = freeBoardCommentRepository.findByFreeBoardCommentNumber(freeBoardCommentNumber);
            if (freeBoardCommentEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_COMMENT_NUMBER);

            boolean isEqualWriter = freeBoardEntity.getWriterUserId().equals(userId);
            if (!isEqualWriter) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);

            boolean matchFreeboardNumber = freeBoardEntity.getFreeBoardNumber() == freeBoardCommentEntity.getFreeBoardNumber();
            if (!matchFreeboardNumber) return ResponseDto.setFail(ResponseMessage.NOT_MATCH_BOARD_NUMBER);

            freeBoardCommentEntity.patch(dto);
            freeBoardCommentRepository.save(freeBoardCommentEntity);

            List<FreeBoardCommentEntity> commentList = freeBoardCommentRepository.findByFreeBoardNumberOrderByWriteDatetimeDesc(freeBoardNumber);
            List<FreeBoardRecommendEntity> recommendList = freeBoardRecommendRepository.findByFreeBoardNumber(freeBoardNumber);

            data = new PatchFreeBoardCommentResponseDto(freeBoardEntity, commentList, recommendList);


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<DeleteFreeBoardCommentResponseDto> deleteFreeBoardComment (String userId, int freeBoardCommentNumber) {
        DeleteFreeBoardCommentResponseDto data = null;

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            FreeBoardCommentEntity freeBoardCommentEntity = freeBoardCommentRepository.findByFreeBoardCommentNumber(freeBoardCommentNumber);
            if (freeBoardCommentEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_COMMENT_NUMBER);
            
            boolean isEqualWriter = freeBoardCommentEntity.getWriterId().equals(userId);
            if (!isEqualWriter) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);
            
            freeBoardCommentRepository.delete(freeBoardCommentEntity);

            int freeBoardNumber = freeBoardCommentEntity.getFreeBoardNumber();
            FreeBoardEntity freeBoardEntity = freeBoardRepository.findByFreeBoardNumber(freeBoardNumber);
            freeBoardEntity.decreaseCommentCount();
            freeBoardRepository.save(freeBoardEntity);

            data = new DeleteFreeBoardCommentResponseDto(true);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<FreeBoardRecommendResponseDto> freeBoardRecommend (String userId, FreeBoardRecommendRequestDto dto) {
        FreeBoardRecommendResponseDto data = null;
        int freeBoardNumber = dto.getFreeBoardNumber();

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            FreeBoardEntity freeBoardEntity = freeBoardRepository.findByFreeBoardNumber(freeBoardNumber);
            if (freeBoardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            FreeBoardRecommendEntity freeBoardRecommendEntity = freeBoardRecommendRepository.findByUserIdAndFreeBoardNumber(userId, freeBoardNumber);
            if(freeBoardRecommendEntity == null) {
                freeBoardRecommendEntity = new FreeBoardRecommendEntity(userEntity, freeBoardNumber);
                freeBoardRecommendRepository.save(freeBoardRecommendEntity);
                freeBoardEntity.increaseRecommendCount();
            }else{
                freeBoardRecommendRepository.delete(freeBoardRecommendEntity);
                freeBoardEntity.decreaseRecommendCount();
            }
            freeBoardRepository.save(freeBoardEntity);

            List<FreeBoardCommentEntity> commentList = freeBoardCommentRepository.findByFreeBoardNumberOrderByWriteDatetimeDesc(freeBoardNumber);
            List<FreeBoardRecommendEntity> recommendList = freeBoardRecommendRepository.findByFreeBoardNumber(freeBoardNumber);

            data = new FreeBoardRecommendResponseDto(freeBoardEntity, commentList, recommendList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
