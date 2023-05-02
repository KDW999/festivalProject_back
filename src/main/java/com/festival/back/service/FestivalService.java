package com.festival.back.service;


import java.util.List;

import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.festival.DeleteOneLineReviewResponseDto;
import com.festival.back.dto.response.festival.GetFestivalAreaListResponseDto;
import com.festival.back.dto.response.festival.GetFestivalMonthResponseDto;
import com.festival.back.dto.response.festival.GetSearchFestivalListResponseDto;
import com.festival.back.dto.response.festival.PatchOneLineReviewResponseDto;
import com.festival.back.dto.response.festival.PostFestivalResponseDto;
import com.festival.back.dto.response.festival.PostOneLineReviewResponseDto;
import com.festival.back.dto.request.festival.PatchOneLineReviewRequestDto;
import com.festival.back.dto.request.festival.PostFestivalRequestDto;
import com.festival.back.dto.request.festival.PostOneLineReviewRequestDto;

public interface FestivalService {

    public ResponseDto<PostFestivalResponseDto> postFestival(String festivalName, PostFestivalRequestDto dto);
    public ResponseDto<PostOneLineReviewResponseDto> postOneLineReview(String userId, PostOneLineReviewRequestDto dto);
    public ResponseDto<PatchOneLineReviewResponseDto> patchOneLineReview(String userId, PatchOneLineReviewRequestDto dto);
    public ResponseDto<DeleteOneLineReviewResponseDto> deleteOneLineReview(int festivalNumber, String userId);
    public ResponseDto<GetSearchFestivalListResponseDto> getSearchFestivalList(String searchWord);
    public ResponseDto<List<GetFestivalAreaListResponseDto>> getFestivalAreaList(String festivalArea);
    public ResponseDto<GetFestivalMonthResponseDto> getFestivalMonthList(int month);
}
