package com.festival.back.service;

import com.festival.back.dto.request.oneLineReview.PostOneLineReviewRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.oneLineReveiw.PostOneLineReviewResponseDto;

public interface OneLineReviewService {
    public ResponseDto<PostOneLineReviewResponseDto> postOneLineReview(String userId, PostOneLineReviewRequestDto dto);
}
