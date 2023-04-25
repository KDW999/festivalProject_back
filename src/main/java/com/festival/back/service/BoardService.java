package com.festival.back.service;

import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.response.PostCommentResponseDto;

import com.festival.back.dto.request.board.RecommendRequestDto;
import com.festival.back.dto.response.board.RecommendResponseDto;

import com.festival.back.dto.response.ResponseDto;
public interface BoardService {

    public ResponseDto<PostCommentResponseDto> postComment(String userId, PostCommentRequestDto dto);
    
    public ResponseDto<RecommendResponseDto> recommend(String id, RecommendRequestDto dto);

    
}
