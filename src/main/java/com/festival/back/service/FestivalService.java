package com.festival.back.service;


import java.util.List;

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
import com.festival.back.dto.request.festival.PatchOneLineReviewRequestDto;
import com.festival.back.dto.request.festival.PostFestivalRequestDto;
import com.festival.back.dto.request.festival.PostOneLineReviewRequestDto;

public interface FestivalService {

    public ResponseDto<PostFestivalResponseDto> postFestival(String festivalName, PostFestivalRequestDto dto);
    public ResponseDto<PostOneLineReviewResponseDto> postOneLineReview(String userId, PostOneLineReviewRequestDto dto);

    public ResponseDto<GetFestivalMonthResponseDto> getFestivalMonthList(int month);
    public ResponseDto<GetFestivalResponseDto> getFestival(int festivalNumber);
    public ResponseDto<GetFestivalNameResponseDto> getFestivalName(int festivalNumber);

    public ResponseDto<PatchOneLineReviewResponseDto> patchOneLineReview(String userId, PatchOneLineReviewRequestDto dto);

    public ResponseDto<DeleteOneLineReviewResponseDto> deleteOneLineReview(int festivalNumber, String userId);

    public ResponseDto<List<GetSearchFestivalListResponseDto>> getSearchFestivalList(String searchWord);
    public ResponseDto<List<GetFestivalAreaListResponseDto>> getFestivalAreaList(String festivalArea);
    public ResponseDto<List<GetOneLineReviewResponseDto>> getOneLineReview(int festivalNumber);
    public ResponseDto<List<GetFestivalTypeListResponseDto>> getFestivalTypeList();
    public ResponseDto<List<GetAllFestivalResponseDto>> getAllFestival();
    public ResponseDto<List<GetTop1OneLineReviewResponseDto>> getTop1OneLineReview();
    public ResponseDto<List<GetFestivalNameListResponseDto>> getFestivalNameList();
    public ResponseDto<List<GetFestivalSearchNameResposneDto>> getFestivalSearchName(String searchName);
}
