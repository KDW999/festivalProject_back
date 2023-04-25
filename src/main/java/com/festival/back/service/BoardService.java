package com.festival.back.service;

import com.festival.back.dto.request.board.PatchCommentRequestDto;
import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.request.board.RecommendRequestDto;
import com.festival.back.dto.response.board.PatchCommentResponseDto;
import com.festival.back.dto.response.board.PostCommentResponseDto;
import com.festival.back.dto.response.board.RecommendResponseDto;

import com.festival.back.dto.response.ResponseDto;

public interface BoardService {

    public ResponseDto<PostCommentResponseDto> postComment(String userId, PostCommentRequestDto dto);
    public ResponseDto<PatchCommentResponseDto> patchComment(String userId, PatchCommentRequestDto dto);
    // public ResponseDto<PostFestivalReviewBoardResponseDto> postReviewBoard(PostReviewBoard dto);
    public ResponseDto<RecommendResponseDto> recommend(String id, RecommendRequestDto dto);

    
}
