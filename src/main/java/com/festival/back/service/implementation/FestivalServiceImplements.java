package com.festival.back.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.dto.request.board.PostFestivalRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.board.GetSearchFestivalListResponseDto;
import com.festival.back.dto.response.board.PostFestivalResponseDto;
import com.festival.back.entity.FestivalEntity;
import com.festival.back.entity.UserEntity;
import com.festival.back.repository.FestivalRepository;
import com.festival.back.dto.request.oneLineReview.PatchOneLineReviewRequestDto;
import com.festival.back.dto.request.oneLineReview.PostOneLineReviewRequestDto;
import com.festival.back.dto.response.oneLineReveiw.DeleteOneLineReviewResponseDto;
import com.festival.back.dto.response.oneLineReveiw.PatchOneLineReviewResponseDto;
import com.festival.back.dto.response.oneLineReveiw.PostOneLineReviewResponseDto;
import com.festival.back.entity.OneLineReviewEntity;
import com.festival.back.entity.SearchwordLogEntity;
import com.festival.back.entity.primaryKey.OneLineReviewPk;
import com.festival.back.repository.OneLineReviewRepository;
import com.festival.back.repository.SearchWordLogRepository;
import com.festival.back.repository.UserRepository;
import com.festival.back.service.FestivalService;

@Service
public class FestivalServiceImplements implements FestivalService {
    @Autowired private UserRepository userRepository;
    @Autowired private FestivalRepository festivalRepository;
    @Autowired private OneLineReviewRepository oneLineReviewRepository;
    @Autowired private SearchWordLogRepository searchWordLogRepository;

    //? 축제 작성
    public ResponseDto<PostFestivalResponseDto> postFestival(String userId, PostFestivalRequestDto dto){
        
        PostFestivalResponseDto data = null;

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            FestivalEntity festivalEntity = new FestivalEntity(userEntity, dto);
            festivalRepository.save(festivalEntity);

            data = new PostFestivalResponseDto(festivalEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        
    }

    //! 수정을 만들긴 했지만 그냥 로그인한 상태로 한 줄 평 달면 이전에 적었던 게 알아서 덮어씌어짐 
    //? 한 줄 평 작성
    public ResponseDto<PostOneLineReviewResponseDto> postOneLineReview(String userId, PostOneLineReviewRequestDto dto) {

        PostOneLineReviewResponseDto data = null;
        int festivalNumber = dto.getFestivalNumber();

        try {

            //? 한 줄 평 작성하려면 로그인 되어있어야 한다.
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            //? 이미 한 줄 평 작성한 작성자가 또 작성할 경우 못쓰게 막기
            boolean hasUserId = oneLineReviewRepository.existsByUserId(userId); //? oneLineReview에 해당 유저 아이디가 있는지 검사
            if(hasUserId) return ResponseDto.setFail(ResponseMessage.EXIST_ID); //? 없으면 한 줄 평 작성하고 있으면 작성 불가
            // OneLineReviewEntity oneLineReviewUserId = oneLineReviewRepository.findByUserId(userId);
            // boolean postedUserId = userId.equals(oneLineReviewUserId.getUserId());
            // if(oneLineReviewUserId != null) return ResponseDto.setFail(ResponseMessage.EXIST_ID);
            
            //? 한 줄 평 작성하려면 축제 번호가 있어야 한다.
            FestivalEntity festivalEntity = festivalRepository.findByFestivalNumber(festivalNumber);
            if(festivalEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_FESTIVAL_NUMBER);
            
            //? 한 줄 평 Entity에 평을 작성한 유저 정보와 한 줄 평 내용들 저장
            OneLineReviewEntity oneLineReviewEntity = new OneLineReviewEntity(userEntity, dto);
            oneLineReviewRepository.save(oneLineReviewEntity); //? 작성한 유저, 축제 번호 pk에 작성 내용들 들어감

            //? 축제 정보에서 한 줄 평 여러 개 보여줘야 되니까 List 형태
            List<OneLineReviewEntity> oneLineReviewList = oneLineReviewRepository.findByFestivalNumberOrderByWriteDatetimeDesc(festivalNumber);

            data = new PostOneLineReviewResponseDto(festivalEntity, oneLineReviewList); //? 만든 데이터들 생성자로 값 넣고 data에 담아서 성공시 보여줌

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 한 줄 평 수정
    public ResponseDto<PatchOneLineReviewResponseDto> patchOneLineReview(String userId, PatchOneLineReviewRequestDto dto) {
        
        PatchOneLineReviewResponseDto data = null;

        //? 어떤 축제 게시물인지 알기 위해 번호 가져오기
        int festivalNumber = dto.getFestivalNumber();

        try {

            //? 축제 게시물이 있는지
            FestivalEntity festivalEntity = festivalRepository.findByFestivalNumber(festivalNumber);
            if(festivalEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_FESTIVAL_NUMBER);

            //? 현재 로그인한 한 줄 평 작성 유저의 정보
            OneLineReviewEntity oneLineReviewEntity = oneLineReviewRepository.findByUserId(userId);
            if(oneLineReviewEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            //? 동일 작성자인지 확인
            boolean isEqualUserId = userId.equals(oneLineReviewEntity.getUserId());
            if(!isEqualUserId) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);

            oneLineReviewEntity.patch(dto);
            oneLineReviewRepository.save(oneLineReviewEntity);

            List<OneLineReviewEntity> oneLineReviewList = oneLineReviewRepository.findByFestivalNumberOrderByWriteDatetimeDesc(festivalNumber);

            data = new PatchOneLineReviewResponseDto(festivalEntity, oneLineReviewList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 한 줄 평 삭제
    public ResponseDto<DeleteOneLineReviewResponseDto> deleteOneLineReview(int festivalNumber, String userId) {

        DeleteOneLineReviewResponseDto data = null;

        try {

            //? 삭제할 한 줄 평의 축제 게시물 번호
            FestivalEntity festivalEntity = festivalRepository.findByFestivalNumber(festivalNumber);
            if(festivalEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_FESTIVAL_NUMBER);

            //? 현재 로그인한 한 줄 평 작성 유저의 정보
            OneLineReviewEntity oneLineReviewEntity = oneLineReviewRepository.findByUserId(userId);
            if(oneLineReviewEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            //? 동일 작성자인지 확인
            boolean isEqualUserId = userId.equals(oneLineReviewEntity.getUserId());
            if(!isEqualUserId) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);

            //? TIL : findById처럼 데이터 변동이 아닌 단순 찾아오는 방식은 큰 문제없으나
            //? delete처럼 데이터에 변동이 생기는 sql문은 각별히 신경써야한다.
            //? oneLineReviewRepository의 PK가 복합키라, 복합키 클래스에 로그인한 userId와 해당 festivalNumber를 매개변수로
            //? 생성자 실행 후 oneLineReview 테이블의 해당 PK로 저장된 레코드를 날린다.
            //? → DB에서 oneLineReview 테이블에 pk가 2개니까 같이 날려야됨? → 해당 게시물의 해당 유저임을 인지하기 위해 pk 2개를 날림
            //? sql 상에서 userId만 딸랑 지우려하면 다른 게시물에 작성한 댓글도 같이날라간다. 
             
            oneLineReviewRepository.deleteById(new OneLineReviewPk(userId, festivalNumber));
            
            data = new DeleteOneLineReviewResponseDto(true);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<GetSearchFestivalListResponseDto> getSearchFestivalList(String searchWord) {
        GetSearchFestivalListResponseDto data= null;

        try {
            SearchwordLogEntity searchwordLogEntity = new SearchwordLogEntity(searchWord);
            searchWordLogRepository.save(searchwordLogEntity);

            List<FestivalEntity> festivalList=festivalRepository.
                   findByFestivalNameContainsOrFestivalTypeContainsOrFestivalInformationContainsOrFestivalAreaOrderByFestivalDurationStartDesc
(searchWord, searchWord, searchWord, searchWord);

            data = new GetSearchFestivalListResponseDto(festivalList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }


}
