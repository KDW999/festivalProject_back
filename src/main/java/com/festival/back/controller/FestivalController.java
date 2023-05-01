package com.festival.back.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.festival.back.common.constant.ApiPattern;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.festival.DeleteOneLineReviewResponseDto;
import com.festival.back.dto.response.festival.GetFestivalAreaListResponseDto;
import com.festival.back.dto.response.festival.GetFestivalMonthResponseDto;
import com.festival.back.dto.response.festival.GetSearchFestivalListResponseDto;
import com.festival.back.dto.response.festival.PatchOneLineReviewResponseDto;
import com.festival.back.dto.response.festival.PostFestivalResponseDto;
import com.festival.back.dto.response.festival.PostOneLineReviewResponseDto;
import com.festival.back.service.FestivalService;
import com.festival.back.dto.request.festival.PatchOneLineReviewRequestDto;
import com.festival.back.dto.request.festival.PostFestivalRequestDto;
import com.festival.back.dto.request.festival.PostOneLineReviewRequestDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "축제 모듈")
@RestController
@RequestMapping(ApiPattern.FESTIVAL)
public class FestivalController {

    @Autowired
    private FestivalService festivalService;

    private final String POST_ONE_LINE_REVIEW = "/one-line-review";
    private final String PATCH_ONE_LINE_REVIEW = "";
    private final String DELETE_ONE_LINE_REVIEW = "/{festivalNumber}";
    private final String GET_SEARCH_FESTIVAL = "/festivalsearch/{searchWord}";
    private final String GET_FESTIVAL_AREA_LIST = "/{festivalArea}";
    private final String GET_FESTIVAL_MONTH="/festivalmonth/{month}";

    private final String POST_FESTIVAL = "";

    // ? 축제 작성
    @ApiOperation(value = "축제 작성", notes =
     "Request Header Authorization에 Bearer JWT를 포함하고 festivalName, festivalType, festivalDurationStart, festivalDurationEnd, festivalTime, festivalArea, festivalCost, festivalInformationUrl을 전송하면 축제 작성 결과로 작성된 정보를 반환, 실패시 실패 메시지 반환")
    @PostMapping(POST_FESTIVAL)
    public ResponseDto<PostFestivalResponseDto> postFestival(
            @ApiParam(hidden = true) @AuthenticationPrincipal String festivalName,
            @Valid @RequestBody PostFestivalRequestDto requestBody) {
        ResponseDto<PostFestivalResponseDto> response = festivalService.postFestival(festivalName, requestBody);
        return response;
    }

    // ? 한 줄 평 작성
    @ApiOperation(value = "한 줄 평 작성", notes = "평점, 한 줄 평가, 축제 번호를 전송하면 한 줄평 작성 결과로 작성된 한 줄 평 정보를 반환, 실패 시 실패 메세지 반환")
    @PostMapping(POST_ONE_LINE_REVIEW)
    public ResponseDto<PostOneLineReviewResponseDto> postOneLineReview(
            @ApiParam(hidden = true) @AuthenticationPrincipal String userId,
            @Valid @RequestBody PostOneLineReviewRequestDto requestBody) {
        ResponseDto<PostOneLineReviewResponseDto> response = festivalService.postOneLineReview(userId, requestBody);
        return response;
    }

    // ? 한 줄 평 수정
    @ApiOperation(value = "특정 한 줄 평 수정", notes = "Request Header에 Authorization에 Bearer JWT를 포함하고 Request Body에 " +
            "average, oneLineReviewContent, festivalNumber를 포함하여 요청하면 성공 시 축제 정보 게시물 전체 데이터 반환, 실패 시 실패 메세지 반환")
    @PatchMapping(PATCH_ONE_LINE_REVIEW)
    public ResponseDto<PatchOneLineReviewResponseDto> patchOneLineReview(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody PatchOneLineReviewRequestDto requestBody) {
        ResponseDto<PatchOneLineReviewResponseDto> response = festivalService.patchOneLineReview(userId, requestBody);
        return response;
    }

    // ? 한 줄 평 삭제
    @ApiOperation(value = "특정 게시물 삭제", notes = "Request Header에 Authorization에 Bearer JWT를 포함하고 Path Variable에 userId를 "
            +
            "포함하여 요청하면 성공 시 true 반환, 실패 시 실패 메세지 반환")
    @DeleteMapping(DELETE_ONE_LINE_REVIEW)
    public ResponseDto<DeleteOneLineReviewResponseDto> deleteOneLineReview(

            @ApiParam(value = "축제 게시물 번호", example = "1", required = true) @PathVariable("festivalNumber") int festivalNumber,

            // ? 로그인하면 유저 정보 갖고있으니 축제 게시물 번호만 url에 넣으면됨
            @AuthenticationPrincipal String userId) {
        ResponseDto<DeleteOneLineReviewResponseDto> response = festivalService.deleteOneLineReview(festivalNumber,userId);
        return response;
    }

    //? 지역별 축제 리스트 가져오기
    @ApiOperation(value = "축제 지역별 리스트 가져오기", notes = "잠시 보류")
    @GetMapping(GET_FESTIVAL_AREA_LIST)
    public ResponseDto<List<GetFestivalAreaListResponseDto>> getFestivalAreaList(
        @ApiParam(value = "축제 지역명", example = "부산", required = true)
        @PathVariable(name = "festivalArea", required = true) String festivalArea){

        ResponseDto<List<GetFestivalAreaListResponseDto>> response = festivalService.getFestivalAreaList(festivalArea);
        return response;
    }

     //? 축제를 검색후 포함한 전체 리스트 반환 -김종빈
        @ApiOperation(value = "축제를 검색한다. festivalNmae fetivalType festivalArea festivalInformaion PathVariable 에 검색어를 입력하고 성공하면 성공값을 반환한다.")
        @GetMapping(GET_SEARCH_FESTIVAL)
        public ResponseDto<GetSearchFestivalListResponseDto> getSearchFestivalList(@PathVariable("searchWord") String searchWord){
            ResponseDto<GetSearchFestivalListResponseDto> response =festivalService.getSearchFestivalList(searchWord);
            return response;

        }

     //? 월별 축제 리스트   
        @GetMapping(GET_FESTIVAL_MONTH)
        public ResponseDto<GetFestivalMonthResponseDto> getFestivalMonthList(@PathVariable("month") int month) {
            ResponseDto<GetFestivalMonthResponseDto> response = festivalService.getFestivalMonthList(month);
            return response;
        }
}
