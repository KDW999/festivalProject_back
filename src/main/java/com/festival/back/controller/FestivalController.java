package com.festival.back.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festival.back.common.constant.ApiPattern;
import com.festival.back.dto.request.board.PostFestivalRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.board.PostFestivalResponseDto;
import com.festival.back.service.FestivalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="축제 모듈")
@RestController
@RequestMapping(ApiPattern.FESTIVAL)
public class FestivalController {
    
    @Autowired private FestivalService festivalService;

    private final String POST_FESTIVAL = "";

    //? 축제 작성
    @ApiOperation(value="축제 작성", notes="이름, 타입, 기간 시작, 기간 끝, 축제 시간, 축제 지역, 축제 비용, 정보 이미지 URL을 전송하면 축제 작성 결과로 작성된 정보를 반환, 실패시 실패 메시지 반환")
    @PostMapping(POST_FESTIVAL)
    public ResponseDto<PostFestivalResponseDto> postFestival(
        @ApiParam(hidden=true)
        @AuthenticationPrincipal String festivalName,
        @Valid @RequestBody PostFestivalRequestDto requestBody
    ) {
        ResponseDto<PostFestivalResponseDto> response = festivalService.postFestival(festivalName, requestBody);
        return response;
    }
}
