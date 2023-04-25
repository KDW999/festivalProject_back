package com.festival.back.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festival.back.common.constant.ApiPattern;
import com.festival.back.dto.request.oneLineReview.PostOneLineReviewRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.oneLineReveiw.PostOneLineReviewResponseDto;
import com.festival.back.service.OneLineReviewService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(ApiPattern.ONE_LINE_REVIEW)
public class OneLineReviewController {
    //? 한 줄 평은 기존에 있던 좋아요, 댓글 달기처럼 축제 정보 게시물 밑에 딸려오는 개념

    @Autowired private OneLineReviewService oneLineReviewService;
    
    private final String POST_ONE_LINE_REVIEW = "/one-line-review";

    //? 한 줄 평 적기
    @ApiOperation(value = "한 줄 평 작성", notes = "평점, 한 줄 평가, 축제 번호를 전송하면 한 줄평 작성 결과로 작성된 한 줄 평 정보를 반환, 실패 시 실패 메세지 반환")
    @PostMapping(POST_ONE_LINE_REVIEW)
    public ResponseDto<PostOneLineReviewResponseDto> postOneLineReview(
    @ApiParam(hidden = true)
    @AuthenticationPrincipal String userId,
    @Valid @RequestBody PostOneLineReviewRequestDto requestBody){
        ResponseDto<PostOneLineReviewResponseDto> response = oneLineReviewService.postOneLineReview(userId, requestBody);
        return response;
    }
}
