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
import com.festival.back.dto.response.freeboard.GetFreeBoardListResponseDto;
import com.festival.back.dto.response.freeboard.GetFreeBoardResponseDto;
import com.festival.back.dto.response.freeboard.GetSearchFreeBoardListResponseDto;
import com.festival.back.dto.response.freeboard.PatchFreeBoardCommentResponseDto;
import com.festival.back.dto.response.freeboard.PatchFreeBoardResponseDto;
import com.festival.back.dto.response.freeboard.PostFreeBoardCommentResponseDto;
import com.festival.back.dto.response.freeboard.PostFreeBoardResponseDto;
import com.festival.back.entity.FreeBoardCommentEntity;
import com.festival.back.entity.FreeBoardEntity;
import com.festival.back.entity.FreeBoardRecommendEntity;
import com.festival.back.entity.SearchwordLogEntity;
import com.festival.back.entity.UserEntity;
import com.festival.back.repository.FreeBoardCommentRepository;
import com.festival.back.repository.FreeBoardRecommendRepository;
import com.festival.back.repository.FreeBoardRepository;
import com.festival.back.repository.SearchWordLogRepository;
import com.festival.back.repository.UserRepository;
import com.festival.back.service.FreeBoardService;

@Service
public class FreeBoardServiceImplements implements FreeBoardService {
    
    @Autowired private UserRepository userRepository;
    @Autowired private FreeBoardRepository freeBoardRepository;
    @Autowired private FreeBoardCommentRepository freeBoardCommentRepository;
    @Autowired private FreeBoardRecommendRepository freeBoardRecommendRepository;
    @Autowired private SearchWordLogRepository searchWordLogRepository;

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

    public ResponseDto<PostFreeBoardCommentResponseDto> postFreeBoardComment(String userId, PostFreeBoardCommentRequestDto dto) {
        
        PostFreeBoardCommentResponseDto data = null;
        int boardNumber = dto.getBoardNumber();

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            FreeBoardEntity freeBoardEntity = freeBoardRepository.findByBoardNumber(boardNumber);
            if (freeBoardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            FreeBoardCommentEntity freeBoardCommentEntity = new FreeBoardCommentEntity(userEntity, dto);
            freeBoardCommentRepository.save(freeBoardCommentEntity);

            freeBoardEntity.increaseCommentCount();
            freeBoardRepository.save(freeBoardEntity);

            List<FreeBoardCommentEntity> commentList = freeBoardCommentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<FreeBoardRecommendEntity> recommendList = freeBoardRecommendRepository.findByBoardNumber(boardNumber);

            data = new PostFreeBoardCommentResponseDto(freeBoardEntity, commentList, recommendList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<FreeBoardRecommendResponseDto> freeBoardRecommend (String userId, FreeBoardRecommendRequestDto dto) {
        
        FreeBoardRecommendResponseDto data = null;
        int boardNumber = dto.getBoardNumber();

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            FreeBoardEntity freeBoardEntity = freeBoardRepository.findByBoardNumber(boardNumber);
            if (freeBoardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            FreeBoardRecommendEntity freeBoardRecommendEntity = freeBoardRecommendRepository.findByUserIdAndBoardNumber(userId, boardNumber);
            if(freeBoardRecommendEntity == null) {
                freeBoardRecommendEntity = new FreeBoardRecommendEntity(userEntity, boardNumber);
                freeBoardRecommendRepository.save(freeBoardRecommendEntity);
                freeBoardEntity.increaseRecommendCount();
            } else {
                freeBoardRecommendRepository.delete(freeBoardRecommendEntity);
                freeBoardEntity.decreaseRecommendCount();
            }
            freeBoardRepository.save(freeBoardEntity);

            List<FreeBoardCommentEntity> commentList = freeBoardCommentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<FreeBoardRecommendEntity> recommendList = freeBoardRecommendRepository.findByBoardNumber(boardNumber);

            data = new FreeBoardRecommendResponseDto(freeBoardEntity, commentList, recommendList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<GetFreeBoardListResponseDto>> getFreeBoardList() {
        
        List<GetFreeBoardListResponseDto> data = null;

        try {
            List<FreeBoardEntity> boardList = freeBoardRepository.findByOrderByBoardWriteDatetimeDesc();
            data = GetFreeBoardListResponseDto.copyList(boardList);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 자유 게시판 검색
    public ResponseDto<List<GetSearchFreeBoardListResponseDto>> getSearchFreeBoardList(String searchWord){
        
        List<GetSearchFreeBoardListResponseDto> data = null;

        try {
            SearchwordLogEntity searchwordLogEntity = new SearchwordLogEntity(searchWord);
            searchWordLogRepository.save(searchwordLogEntity);

            List<FreeBoardEntity> freeBoardEntity = freeBoardRepository.findByBoardTitleContainsOrBoardContentContainsOrderByBoardWriteDatetimeDesc(searchWord, searchWord);
            if(freeBoardEntity.isEmpty()) return ResponseDto.setFail(ResponseMessage.NO_SEARCH_RESULTS);

            data = GetSearchFreeBoardListResponseDto.copyList(freeBoardEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<GetFreeBoardResponseDto> getFreeBoard(int boardNumber) {
        
        GetFreeBoardResponseDto data = null;

        try {
            FreeBoardEntity freeBoardEntity = freeBoardRepository.findByBoardNumber(boardNumber);
            if (freeBoardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            List<FreeBoardCommentEntity> commentList = freeBoardCommentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<FreeBoardRecommendEntity> recommendList = freeBoardRecommendRepository.findByBoardNumber(boardNumber);

            freeBoardEntity.increaseViewCount();
            freeBoardRepository.save(freeBoardEntity);

            data = new GetFreeBoardResponseDto(freeBoardEntity, commentList, recommendList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<PatchFreeBoardResponseDto> patchFreeBoard(String userId, PatchFreeBoardRequestDto dto) {
        PatchFreeBoardResponseDto data = null;
        int boardNumber = dto.getBoardNumber();

        try {
            FreeBoardEntity freeBoardEntity = freeBoardRepository.findByBoardNumber(boardNumber);
            if(freeBoardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            boolean isEqualWriter = freeBoardEntity.getWriterUserId().equals(userId);
            if (!isEqualWriter) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);

            List<FreeBoardCommentEntity> commentList = freeBoardCommentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<FreeBoardRecommendEntity> recommendList = freeBoardRecommendRepository.findByBoardNumber(boardNumber);

            freeBoardEntity.patch(dto);
            freeBoardRepository.save(freeBoardEntity);

            data = new PatchFreeBoardResponseDto(freeBoardEntity, commentList, recommendList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<PatchFreeBoardCommentResponseDto> patchFreeBoardComment (String userId, PatchFreeBoardCommentRequestDto dto) {
        
        PatchFreeBoardCommentResponseDto data = null;
        int boardNumber = dto.getBoardNumber();
        int commentNumber = dto.getCommentNumber();

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            FreeBoardEntity freeBoardEntity = freeBoardRepository.findByBoardNumber(boardNumber);
            if (freeBoardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            FreeBoardCommentEntity freeBoardCommentEntity = freeBoardCommentRepository.findByCommentNumber(commentNumber);
            if (freeBoardCommentEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_COMMENT_NUMBER);

            boolean isEqualWriter = freeBoardEntity.getWriterUserId().equals(userId);
            if (!isEqualWriter) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);

            boolean matchBoardNumber = freeBoardEntity.getBoardNumber() == freeBoardCommentEntity.getBoardNumber();
            if (!matchBoardNumber) return ResponseDto.setFail(ResponseMessage.NOT_MATCH_BOARD_NUMBER);

            freeBoardCommentEntity.patch(dto);
            freeBoardCommentRepository.save(freeBoardCommentEntity);

            List<FreeBoardCommentEntity> commentList = freeBoardCommentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<FreeBoardRecommendEntity> recommendList = freeBoardRecommendRepository.findByBoardNumber(boardNumber);

            data = new PatchFreeBoardCommentResponseDto(freeBoardEntity, commentList, recommendList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<DeleteFreeBoardResponseDto> deleteFreeBoard(String userId, int boardNumber) {
        
        DeleteFreeBoardResponseDto data = null;

        try {
            FreeBoardEntity freeBoardEntity = freeBoardRepository.findByBoardNumber(boardNumber);
            if (freeBoardEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_BOARD);

            boolean isEqualWriter = freeBoardEntity.getWriterUserId().equals(userId);
            if (!isEqualWriter) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);

            freeBoardCommentRepository.deleteByBoardNumber(boardNumber);
            freeBoardRecommendRepository.deleteByBoardNumber(boardNumber);

            freeBoardRepository.delete(freeBoardEntity);
            data = new DeleteFreeBoardResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<DeleteFreeBoardCommentResponseDto> deleteFreeBoardComment (String userId, int boardCommentNumber) {
        
        DeleteFreeBoardCommentResponseDto data = null;

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            FreeBoardCommentEntity freeBoardCommentEntity = freeBoardCommentRepository.findByCommentNumber(boardCommentNumber);
            if (freeBoardCommentEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_COMMENT_NUMBER);
            
            boolean isEqualWriter = freeBoardCommentEntity.getWriterUserId().equals(userId);
            if (!isEqualWriter) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);
            
            freeBoardCommentRepository.delete(freeBoardCommentEntity);

            int boardNumber = freeBoardCommentEntity.getBoardNumber();
            FreeBoardEntity freeBoardEntity = freeBoardRepository.findByBoardNumber(boardNumber);
            freeBoardEntity.decreaseCommentCount();
            freeBoardRepository.save(freeBoardEntity);

            data = new DeleteFreeBoardCommentResponseDto(true);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
