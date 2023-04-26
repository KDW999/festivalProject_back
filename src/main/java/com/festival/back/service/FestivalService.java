package com.festival.back.service;

import com.festival.back.dto.request.oneLineReview.PatchOneLineReviewRequestDto;
import com.festival.back.dto.request.oneLineReview.PostOneLineReviewRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.oneLineReveiw.DeleteOneLineReviewResponseDto;
import com.festival.back.dto.response.oneLineReveiw.PatchOneLineReviewResponseDto;
import com.festival.back.dto.response.oneLineReveiw.PostOneLineReviewResponseDto;

public interface FestivalService {
    public ResponseDto<PostOneLineReviewResponseDto> postOneLineReview(String userId, PostOneLineReviewRequestDto dto);
    public ResponseDto<PatchOneLineReviewResponseDto> patchOneLineReview(String userId, PatchOneLineReviewRequestDto dto);
    public ResponseDto<DeleteOneLineReviewResponseDto> deleteOneLineReview(int festivalNumber, String userId);
}
