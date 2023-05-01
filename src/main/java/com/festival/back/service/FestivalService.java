package com.festival.back.service;


import java.util.List;

import com.festival.back.dto.request.board.PostFestivalRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.board.GetFestivalAreaListResponseDto;
import com.festival.back.dto.response.board.GetSearchFestivalListResponseDto;
import com.festival.back.dto.response.board.PostFestivalResponseDto;
import com.festival.back.dto.request.oneLineReview.PatchOneLineReviewRequestDto;
import com.festival.back.dto.request.oneLineReview.PostOneLineReviewRequestDto;

import com.festival.back.dto.response.oneLineReveiw.DeleteOneLineReviewResponseDto;
import com.festival.back.dto.response.oneLineReveiw.PatchOneLineReviewResponseDto;
import com.festival.back.dto.response.oneLineReveiw.PostOneLineReviewResponseDto;

public interface FestivalService {
    public ResponseDto<PostFestivalResponseDto> postFestival(String festivalName, PostFestivalRequestDto dto);
    public ResponseDto<PostOneLineReviewResponseDto> postOneLineReview(String userId, PostOneLineReviewRequestDto dto);
    public ResponseDto<PatchOneLineReviewResponseDto> patchOneLineReview(String userId, PatchOneLineReviewRequestDto dto);
    public ResponseDto<DeleteOneLineReviewResponseDto> deleteOneLineReview(int festivalNumber, String userId);
    public ResponseDto<GetSearchFestivalListResponseDto> getSearchFestivalList(String searchWord);
    public ResponseDto<List<GetFestivalAreaListResponseDto>> getFestivalAreaList(String festivalArea);


}
