package com.festival.back.service;

import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.request.board.PostReviewBoardRequestDto;
import com.festival.back.dto.request.board.RecommendRequestDto;
import com.festival.back.dto.response.board.PostCommentResponseDto;
import com.festival.back.dto.response.board.PostFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.board.RecommendResponseDto;

public interface BoardService {


    public ResponseDto<PostCommentResponseDto> postComment(String userId, PostCommentRequestDto dto);
    public ResponseDto<RecommendResponseDto> recommend(String id, RecommendRequestDto dto);

    public ResponseDto<PostFestivalReviewBoardResponseDto> postFestivalReviewBoard(String userId,PostReviewBoardRequestDto dto);

    
}
