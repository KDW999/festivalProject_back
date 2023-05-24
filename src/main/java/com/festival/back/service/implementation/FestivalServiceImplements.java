package com.festival.back.service.implementation;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.common.util.AreaSet;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.festival.DeleteOneLineReviewResponseDto;
import com.festival.back.dto.response.festival.GetFestivalNameListResponseDto;
import com.festival.back.dto.response.festival.GetAllFestivalResponseDto;
import com.festival.back.dto.response.festival.GetFestivalAreaListResponseDto;
import com.festival.back.dto.response.festival.GetFestivalMonthResponseDto;
import com.festival.back.dto.response.festival.GetFestivalNameResponseDto;
import com.festival.back.dto.response.festival.GetFestivalResponseDto;
import com.festival.back.dto.response.festival.GetFestivalSearchNameResposneDto;
import com.festival.back.dto.response.festival.GetFestivalTypeListResponseDto;
import com.festival.back.dto.response.festival.GetOneLineReviewResponseDto;
import com.festival.back.dto.response.festival.GetSearchFestivalListResponseDto;
import com.festival.back.dto.response.festival.GetTop1OneLineReviewResponseDto;
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
    public ResponseDto<PostFestivalResponseDto> postFestival(String userId, PostFestivalRequestDto dto) {
        
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
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            boolean hasUserId = oneLineReviewRepository.existsByUserIdAndFestivalNumber(userId, festivalNumber);
            if(hasUserId) return ResponseDto.setFail(ResponseMessage.EXIST_ID);
            
            OneLineReviewEntity oneLineReviewEntity = new OneLineReviewEntity(userEntity, dto);
            oneLineReviewRepository.save(oneLineReviewEntity);
            
            int festivalAvg = festivalRepository.setAverger(festivalNumber,festivalNumber);

            FestivalEntity festivalEntity = festivalRepository.findByFestivalNumber(festivalNumber);
            if(festivalEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_FESTIVAL_NUMBER);

            List<OneLineReviewEntity> oneLineReviewList = oneLineReviewRepository.findByFestivalNumberOrderByWriteDatetimeDesc(festivalNumber);

            data = new PostOneLineReviewResponseDto(festivalEntity, oneLineReviewList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 한 줄 평 수정
    public ResponseDto<PatchOneLineReviewResponseDto> patchOneLineReview(String userId, PatchOneLineReviewRequestDto dto) {
        
        PatchOneLineReviewResponseDto data = null;

        int festivalNumber = dto.getFestivalNumber();

        try {
            FestivalEntity festivalEntity = festivalRepository.findByFestivalNumber(festivalNumber);
            if(festivalEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_FESTIVAL_NUMBER);

            OneLineReviewEntity oneLineReviewEntity = oneLineReviewRepository.findByUserId(userId);
            if(oneLineReviewEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

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
            FestivalEntity festivalEntity = festivalRepository.findByFestivalNumber(festivalNumber);
            if(festivalEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_FESTIVAL_NUMBER);

            OneLineReviewEntity oneLineReviewEntity = oneLineReviewRepository.findByUserId(userId);
            if(oneLineReviewEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            boolean isEqualUserId = userId.equals(oneLineReviewEntity.getUserId());
            if(!isEqualUserId) return ResponseDto.setFail(ResponseMessage.NOT_PERMISSION);
            
            oneLineReviewRepository.deleteById(new OneLineReviewPk(userId, festivalNumber));
            
            data = new DeleteOneLineReviewResponseDto(true);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 검색한 축제 리스트 조회
    public ResponseDto<List<GetSearchFestivalListResponseDto>> getSearchFestivalList(String searchWord) {
        
        List<GetSearchFestivalListResponseDto> data = null;

        try {
            SearchwordLogEntity searchwordLogEntity = new SearchwordLogEntity(searchWord);
            searchWordLogRepository.save(searchwordLogEntity);
    
            List<FestivalEntity> festivalList = festivalRepository.
                findByFestivalNameContainsOrFestivalTypeContainsOrFestivalInformationContainsOrFestivalAreaOrderByFestivalDurationStartDesc
                (searchWord, searchWord, searchWord, searchWord);
            if(festivalList.isEmpty()) return ResponseDto.setFail(ResponseMessage.NO_SEARCH_RESULTS);
                
            data = GetSearchFestivalListResponseDto.copyList(festivalList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 지역별 축제 리스트
    public ResponseDto<List<GetFestivalAreaListResponseDto>> getFestivalAreaList(String festivalArea) {
        
        List<GetFestivalAreaListResponseDto> data = null;

        try {
            List<String> festivalAreaList = AreaSet.getAreaList(festivalArea);

            List<FestivalEntity> areaList = new ArrayList<>();
            for (String searchArea: festivalAreaList) {
                List<FestivalEntity> festivalList = festivalRepository.findByFestivalAreaContainingOrderByFestivalDurationStart(searchArea);
                areaList.addAll(festivalList);
            }

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
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String monthString = decimalFormat.format(month);
        String nextMonthString = decimalFormat.format(month + 1);
        LocalDate now = LocalDate.now();
        String monthDate = now.getYear() + "-" + monthString + "-01";
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

    //  ? 특정 한줄평가 후기만 불러옴
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

    //? 전체 축제 리스트 불러오기
    public ResponseDto<List<GetAllFestivalResponseDto>> getAllFestival() {

        List<GetAllFestivalResponseDto> data = null;
        
        try{
            List<FestivalEntity> festivalEntityList = festivalRepository.findByOrderByFestivalDurationStartAsc();
            data = GetAllFestivalResponseDto.copyList(festivalEntityList);

        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        } 
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<GetFestivalTypeListResponseDto>> getFestivalTypeList() {
        
        List<GetFestivalTypeListResponseDto> data = null;

        try {
            List<String> festivalEntity = festivalRepository.getFestivalTypeList();
            data = GetFestivalTypeListResponseDto.copyList(festivalEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<GetTop1OneLineReviewResponseDto>> getTop1OneLineReview() {
        
        List<GetTop1OneLineReviewResponseDto> data = null;
        
        LocalDate now = LocalDate.now();
        String monthValue = now.getMonthValue() < 10 ? "0" + now.getMonthValue() : "" + now.getMonthValue();
        String monthNextValue = now.getMonthValue() + 1 < 10 ? "0" + (now.getMonthValue() + 1) : "" + (now.getMonthValue() + 1);
        String nowMonth = now.getYear() + "-" + monthValue + "-01";
        String nextMOnth = now.getYear() + "-" + monthNextValue + "-01";

        try {
            FestivalEntity festivalEntity = festivalRepository.getTop1OneLineReview(nowMonth,nextMOnth);
            List<OneLineReviewEntity> oneLineReviewList=oneLineReviewRepository.findByFestivalNumberOrderByWriteDatetimeDesc(festivalEntity.getFestivalNumber());
            data = GetTop1OneLineReviewResponseDto.copyList(oneLineReviewList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<GetFestivalNameResponseDto> getFestivalName(int festivalNumber) {
        
        GetFestivalNameResponseDto data = null;

        try {
            FestivalEntity festivalEntity = festivalRepository.findByFestivalNumber(festivalNumber);
            if(festivalEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_FESTIVAL_NUMBER);
            String festivalName = festivalEntity.getFestivalName();
            data = new GetFestivalNameResponseDto(festivalName);
            
        } catch (Exception exception) {
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<GetFestivalNameListResponseDto>> getFestivalNameList() {
        
        List<GetFestivalNameListResponseDto> data = null;

        try {
            List<FestivalEntity> festivalEntity = festivalRepository.findBy();
            data = GetFestivalNameListResponseDto.copyList(festivalEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<GetFestivalSearchNameResposneDto>> getFestivalSearchName(String searchName) {
        
        List<GetFestivalSearchNameResposneDto> data = null;

        try {
            List<FestivalEntity> festivalEntity = festivalRepository.searchName(searchName);
            data = GetFestivalSearchNameResposneDto.copyList(festivalEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
            
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}