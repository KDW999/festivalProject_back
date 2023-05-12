package com.festival.back.service.implementation;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.festival.DeleteOneLineReviewResponseDto;
import com.festival.back.dto.response.festival.GetFestivalAreaListResponseDto;
import com.festival.back.dto.response.festival.GetFestivalMonthResponseDto;
import com.festival.back.dto.response.festival.GetFestivalResponseDto;
import com.festival.back.dto.response.festival.GetFestivalTypeListResponseDto;
import com.festival.back.dto.response.festival.GetOneLineReviewResponseDto;
import com.festival.back.dto.response.festival.GetSearchFestivalListResponseDto;
import com.festival.back.dto.response.festival.PatchOneLineReviewResponseDto;
import com.festival.back.dto.response.festival.PostFestivalResponseDto;
import com.festival.back.dto.response.festival.PostOneLineReviewResponseDto;
import com.festival.back.entity.FestivalEntity;
import com.festival.back.entity.UserEntity;
import com.festival.back.repository.FestivalRepository;
import com.festival.back.dto.request.festival.PatchOneLineReviewRequestDto;
import com.festival.back.dto.request.festival.PostFestivalRequestDto;
import com.festival.back.dto.request.festival.PostOneLineReviewRequestDto;
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
            boolean hasFestivalNumber = oneLineReviewRepository.existsByFestivalNumber(festivalNumber); //? 다른 축제 한줄 평 작성을 위한 조건 추가
            if(hasUserId && hasFestivalNumber) return ResponseDto.setFail(ResponseMessage.EXIST_ID); //? 없으면 한 줄 평 작성하고 있으면 작성 불가
            
            //? 한 줄 평 Entity에 평을 작성한 유저 정보와 한 줄 평 내용들 저장
            OneLineReviewEntity oneLineReviewEntity = new OneLineReviewEntity(userEntity, dto);
            oneLineReviewRepository.save(oneLineReviewEntity); //? 작성한 유저, 축제 번호 pk에 작성 내용들 들어감
            
            int festivalAvg = festivalRepository.setAverger(festivalNumber,festivalNumber);
              
           
            //? 한 줄 평 작성하려면 축제 번호가 있어야 한다.
            FestivalEntity festivalEntity = festivalRepository.findByFestivalNumber(festivalNumber);
            if(festivalEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_FESTIVAL_NUMBER);

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

            //^ 삭제한 한 줄 평의 페스티벌 게시물 정보도 보여주기
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

            int festivalAvg = festivalRepository.setAverger(festivalNumber,festivalNumber);
            
            data = new DeleteOneLineReviewResponseDto(true);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 검색한 축제 리스트 조회
    public ResponseDto<GetSearchFestivalListResponseDto> getSearchFestivalList(String searchWord) {
        GetSearchFestivalListResponseDto data= null;

    

        try {
            SearchwordLogEntity searchwordLogEntity = new SearchwordLogEntity(searchWord);
            searchWordLogRepository.save(searchwordLogEntity);
    
            List<FestivalEntity> festivalList=festivalRepository.
                findByFestivalNameContainsOrFestivalTypeContainsOrFestivalInformationContainsOrFestivalAreaOrderByFestivalDurationStartDesc
                (searchWord, searchWord, searchWord, searchWord);
                if(festivalList.isEmpty()) return ResponseDto.setFail(ResponseMessage.NO_SEARCH_RESULTS);
                

            data = new GetSearchFestivalListResponseDto(festivalList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 지역별 축제 리스트
    //^ 프론트에 붙인다면 Axios로 이 url을 그냥 연결하기?
    public ResponseDto<List<GetFestivalAreaListResponseDto>> getFestivalAreaList(String festivalArea) {
        
        List<GetFestivalAreaListResponseDto> data = null;

        try {

            //? findByFestivalAreaOrderBy -> findByFestivalAreaContainingOrderBy로 바꿨음
            //? Containing이 SQL에서 LIKE와 같은 기능
            List<FestivalEntity> areaList = festivalRepository.findByFestivalAreaContainingOrderByFestivalDurationStart(festivalArea);
            data = GetFestivalAreaListResponseDto.copyList(areaList);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 월별 축제 리스트
    public ResponseDto<GetFestivalMonthResponseDto> getFestivalMonthList(int month) { 
    
        GetFestivalMonthResponseDto data= null;
        // ? DecimalFormat 사용해서 입력 받은(month) 값을 1 로 받는다면 01 로 포멧을 변경 시킴.
        DecimalFormat decimalFormat = new DecimalFormat("00");
        // ? monthString =변수명으로 월(month) 를 입력받음.
        String monthString = decimalFormat.format(month);
        // ? nextMonthString =변수명으로 다음월(month)를 받아서 +1 증가 시킴 (다음달)
        String nextMonthString = decimalFormat.format(month + 1);
        // ? now 에 현제 시간을 받음.
        LocalDate now = LocalDate.now();
        // ? monthDate 는 현제 년도- 위에서 받은 monthString 값+ 01" 1월달을 입력받으면2023-01-01 monthDate 가 생성됨.
        String monthDate = now.getYear() + "-" + monthString + "-01";
        // ? 다음달을 출력 하는 데이터임. 
        // ? 12월달 이라면 year 에 1년을 증가시키고 1월1일 값을 받고 아니랄면 현재 년도 증가시킨 달 01 일 을 입력받음.
        String nextMonthDate = month == 12 ? now.getYear() + 1 + "-01-01" : now.getYear() + "-" + nextMonthString + "-01";

        try {

            List<FestivalEntity> festivalEntity =  festivalRepository.getFestivalMonth(monthDate, monthDate, monthDate, nextMonthDate);
            data= new GetFestivalMonthResponseDto(festivalEntity);

            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //  ? 특정 한줄평가 후기만  불러옴
    public ResponseDto<List<GetOneLineReviewResponseDto>> getOneLineReview(int festivalNumber) {
        List<GetOneLineReviewResponseDto> data = null;

        try {

            List<OneLineReviewEntity> oneLineReviewEntity = oneLineReviewRepository.findByFestivalNumberOrderByWriteDatetimeDesc(festivalNumber);

            data = GetOneLineReviewResponseDto.copyList(oneLineReviewEntity);

            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        
    }
    //  ? 특정 축제만 불러옴.
    public ResponseDto<GetFestivalResponseDto> getFestival(int festivalNumber) {
    GetFestivalResponseDto data= null;

    try {
        FestivalEntity festivalEntity=festivalRepository.findByFestivalNumber(festivalNumber);

        data=new GetFestivalResponseDto(festivalEntity);
        
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
    }
    return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        
    }

    public ResponseDto<List<GetFestivalTypeListResponseDto>> getFestivalTypeList() {
        List<GetFestivalTypeListResponseDto> data = null;

        try {
            List<FestivalEntity> festivalEntity = festivalRepository.findByOrderByFestivalTypeDesc();
            data = GetFestivalTypeListResponseDto.copyList(festivalEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}