package com.festival.back.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festival.back.common.constant.ApiPattern;
import com.festival.back.dto.request.PostReviewBoardRequestDto;
import com.festival.back.dto.response.PostFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.service.BoardService;

@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {
    @Autowired BoardService boardService;

    private final String POST_FESTIVAL_REVIEW_BOARD = "";

    
    @PostMapping(POST_FESTIVAL_REVIEW_BOARD)
    public ResponseDto<PostFestivalReviewBoardResponseDto> postReviewBoard(String userId,@Valid @RequestBody PostReviewBoardRequestDto requestbody){
        ResponseDto<PostFestivalReviewBoardResponseDto> response=boardService.postReviewBoard(requestbody,userId);
        return response;
        
    }
    
}
