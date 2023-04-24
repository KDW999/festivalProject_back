package com.festival.back.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festival.back.common.constant.ApiPattern;
import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.response.PostCommentResponseDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.service.BoardService;

@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {

    @Autowired private BoardService boardService;

    private final String POST_COMMENT = "/comment";

    @PostMapping(POST_COMMENT)
    public ResponseDto<PostCommentResponseDto> postComment(
        @AuthenticationPrincipal String id,
        @Valid @RequestBody PostCommentRequestDto requestBody
    ) {
        ResponseDto<PostCommentResponseDto> response = boardService.postComment(id, requestBody);
        return response;
    }
}
