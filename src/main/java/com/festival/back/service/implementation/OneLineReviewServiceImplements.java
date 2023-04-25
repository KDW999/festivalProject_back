package com.festival.back.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.dto.request.oneLineReview.PostOneLineReviewRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.oneLineReveiw.PostOneLineReviewResponseDto;
import com.festival.back.entity.FestivalEntity;
import com.festival.back.entity.OneLineReviewEntity;
import com.festival.back.entity.UserEntity;
import com.festival.back.repository.FestivalRepository;
import com.festival.back.repository.OneLineReviewRepository;
import com.festival.back.repository.UserRepository;
import com.festival.back.service.OneLineReviewService;

@Service
public class OneLineReviewServiceImplements implements OneLineReviewService {

    @Autowired private UserRepository userRepository;
    @Autowired private FestivalRepository festivalRepository;
    @Autowired private OneLineReviewRepository oneLineReviewRepository;


    //? 한 줄 평 작성
    public ResponseDto<PostOneLineReviewResponseDto> postOneLineReview(String userId, PostOneLineReviewRequestDto dto) {

        PostOneLineReviewResponseDto data = null;
        int festivalNumber = dto.getFestivalNumber();

        try {

            //? 한 줄 평 작성하려면 로그인 되어있어야 한다.
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);
            
            //? 한 줄 평 작성하려면 축제 번호가 있어야 한다.
            FestivalEntity festivalEntity = festivalRepository.findByFestivalNumber(festivalNumber);
            if(festivalEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_FESTIVAL_NUMBER);
            
            //? 한 줄 평 Entity에 작성한 유저 정보와 한 줄 평 내용들 저장
            OneLineReviewEntity oneLineReviewEntity = new OneLineReviewEntity(userEntity, dto);
            oneLineReviewRepository.save(oneLineReviewEntity); //? 작성한 유저, 축제 번호 pk에 작성 내용들 들어감

            //? 축제 정보에서 한 줄 평 여러 개 보여줘야 되니까 List 형태
            List<OneLineReviewEntity> oneLineReviewList = oneLineReviewRepository.findByFestivalNumberOrderByWriteDatetimeDesc(festivalNumber);

            data = new PostOneLineReviewResponseDto(festivalEntity, oneLineReviewList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
}
