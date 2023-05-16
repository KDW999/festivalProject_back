package com.festival.back.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festival.back.common.constant.ApiPattern;
import com.festival.back.dto.request.freeboard.PatchFreeBoardRequestDto;
import com.festival.back.dto.request.freeboard.PostFreeBoardRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.freeboard.PatchFreeBoardResponseDto;
import com.festival.back.dto.response.freeboard.PostFreeBoardResponseDto;
import com.festival.back.service.FreeBoardService;

@RestController
@RequestMapping(ApiPattern.FREE_BOARD)
public class FreeBoardController {
    
    @Autowired private FreeBoardService freeBoardService;

    private final String POST_FREE_BOARD = "";
    private final String PATCH_FREE_BOARD = "";

    @PostMapping(POST_FREE_BOARD)
    public ResponseDto<PostFreeBoardResponseDto> postFreeBoard(@AuthenticationPrincipal String userId,
    @Valid @RequestBody PostFreeBoardRequestDto requestBody) {
        ResponseDto<PostFreeBoardResponseDto> response = freeBoardService.postFreeBoard(userId,requestBody);
        return response; 
    }

    @PatchMapping(PATCH_FREE_BOARD)
    public ResponseDto<PatchFreeBoardResponseDto> patchFreeBoard(@AuthenticationPrincipal String userId, @Valid @RequestBody PatchFreeBoardRequestDto requestBody) {
        ResponseDto<PatchFreeBoardResponseDto> response = freeBoardService.patchFreeBoard(userId, requestBody);
        return response;
    }

}
