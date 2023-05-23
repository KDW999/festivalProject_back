package com.festival.back.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festival.back.common.constant.ApiPattern;
import com.festival.back.dto.request.freeboard.FreeBoardRecommendRequestDto;
import com.festival.back.dto.request.freeboard.PatchFreeBoardCommentRequestDto;
import com.festival.back.dto.request.freeboard.PatchFreeBoardRequestDto;
import com.festival.back.dto.request.freeboard.PostFreeBoardCommentRequestDto;
import com.festival.back.dto.request.freeboard.PostFreeBoardRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.freeboard.DeleteFreeBoardCommentResponseDto;
import com.festival.back.dto.response.freeboard.DeleteFreeBoardResponseDto;
import com.festival.back.dto.response.freeboard.FreeBoardRecommendResponseDto;
import com.festival.back.dto.response.freeboard.GetFreeBoardListResponseDto;
import com.festival.back.dto.response.freeboard.GetFreeBoardResponseDto;
import com.festival.back.dto.response.freeboard.GetSearchFreeBoardListResponseDto;
import com.festival.back.dto.response.freeboard.PatchFreeBoardCommentResponseDto;
import com.festival.back.dto.response.freeboard.PatchFreeBoardResponseDto;
import com.festival.back.dto.response.freeboard.PostFreeBoardCommentResponseDto;
import com.festival.back.dto.response.freeboard.PostFreeBoardResponseDto;
import com.festival.back.service.FreeBoardService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(ApiPattern.FREE_BOARD)
public class FreeBoardController {
    
    @Autowired private FreeBoardService freeBoardService;

    private final String FREE_BOARD_RECOMMEND = "/recommend";
    private final String POST_FREE_BOARD = "";
    private final String POST_FREE_BOARD_COMMENT = "/comment";

    private final String GET_FREE_BOARD_LIST = "";
    private final String GET_FREE_BOARD = "/{boardNumber}";
    private final String GET_SEARCH_FREE_BOARD_LIST = "/search-free-board/{searchWord}";

    private final String PATCH_FREE_BOARD = "";
    private final String PATCH_FREE_BOARD_COMMENT = "/comment";

    private final String DELETE_FREE_BOARD = "/{boardNumber}";
    private final String DELETE_FREE_BOARD_COMMENT = "/comment/{commentNumber}";

    @PostMapping(FREE_BOARD_RECOMMEND)
    public ResponseDto<FreeBoardRecommendResponseDto> freeBoardRecommend (@AuthenticationPrincipal String userId, @Valid @RequestBody FreeBoardRecommendRequestDto requestBody) {
        ResponseDto<FreeBoardRecommendResponseDto> response = freeBoardService.freeBoardRecommend(userId, requestBody);
        return response;
    }

    @PostMapping(POST_FREE_BOARD)
    public ResponseDto<PostFreeBoardResponseDto> postFreeBoard(@AuthenticationPrincipal String userId,
    @Valid @RequestBody PostFreeBoardRequestDto requestBody) {
        ResponseDto<PostFreeBoardResponseDto> response = freeBoardService.postFreeBoard(userId,requestBody);
        return response; 
    }

    @PostMapping(POST_FREE_BOARD_COMMENT)
    public ResponseDto<PostFreeBoardCommentResponseDto> postFreeBoardComment(@AuthenticationPrincipal String userId, @Valid @RequestBody PostFreeBoardCommentRequestDto requestBody) {
        ResponseDto<PostFreeBoardCommentResponseDto> response = freeBoardService.postFreeBoardComment(userId, requestBody);
        return response;
    }

    @GetMapping(GET_FREE_BOARD_LIST)
    public ResponseDto<List<GetFreeBoardListResponseDto>> getFreeBoardList () {
        ResponseDto<List<GetFreeBoardListResponseDto>> response = freeBoardService.getFreeBoardList();
        return response;
    }

    @GetMapping(GET_FREE_BOARD)
    public ResponseDto<GetFreeBoardResponseDto> getFreeBoard(@PathVariable("boardNumber") int boardNumber) {
        ResponseDto<GetFreeBoardResponseDto> response = freeBoardService.getFreeBoard(boardNumber);
        return response;
    }

    @ApiOperation(value = "자유 게시판 검색 기능", notes = "Path Variable에 searchWord를 포함하여 요청, 성공 시 검색어와 관련된 자유 게시물 List 반환, 실패 시 실패 메세지 반환")
    @GetMapping(GET_SEARCH_FREE_BOARD_LIST)
    public ResponseDto<List<GetSearchFreeBoardListResponseDto>> getSearchFreeBoardList(@PathVariable("searchWord") String searchWord){
        ResponseDto<List<GetSearchFreeBoardListResponseDto>> response = freeBoardService.getSearchFreeBoardList(searchWord);
        return response;
    }


    @PatchMapping(PATCH_FREE_BOARD)
    public ResponseDto<PatchFreeBoardResponseDto> patchFreeBoard(@AuthenticationPrincipal String userId, @Valid @RequestBody PatchFreeBoardRequestDto requestBody) {
        ResponseDto<PatchFreeBoardResponseDto> response = freeBoardService.patchFreeBoard(userId, requestBody);
        return response;
    }

    @PatchMapping(PATCH_FREE_BOARD_COMMENT)
    public ResponseDto<PatchFreeBoardCommentResponseDto> patchFreeBoardComment(@AuthenticationPrincipal String userId, @Valid @RequestBody PatchFreeBoardCommentRequestDto requestBody) {
        ResponseDto<PatchFreeBoardCommentResponseDto> response = freeBoardService.patchFreeBoardComment(userId, requestBody);
        return response;
    }

    @DeleteMapping(DELETE_FREE_BOARD)
    public ResponseDto<DeleteFreeBoardResponseDto> deleteFreeBoard(
    @ApiParam(hidden = true) @AuthenticationPrincipal String userId, 
    @ApiParam(value = "게시물 번호",example = "1",required = true)
    @PathVariable("boardNumber")int boardNumber){
        ResponseDto<DeleteFreeBoardResponseDto> response = freeBoardService.deleteFreeBoard(userId, boardNumber);
        return response;
    }

    @DeleteMapping(DELETE_FREE_BOARD_COMMENT)
    public ResponseDto<DeleteFreeBoardCommentResponseDto> deleteFreeBoardComment(
    @ApiParam(hidden = true) @AuthenticationPrincipal String userId, 
    @ApiParam(value = "게시물 번호",example = "1", required = true)
    @PathVariable("commentNumber")int commentNumber) {
        ResponseDto<DeleteFreeBoardCommentResponseDto> response = freeBoardService.deleteFreeBoardComment(userId, commentNumber);
        return response;
    }
}
