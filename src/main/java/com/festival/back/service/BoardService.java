package com.festival.back.service;

import com.festival.back.dto.ResponseDto;
import com.festival.back.dto.request.board.RecommendRequestDto;
import com.festival.back.dto.response.board.RecommendResponseDto;

public interface BoardService {

    // public ResponseDto<PostFestivalReviewBoardResponseDto> postReviewBoard(PostReviewBoard dto);
    public ResponseDto<RecommendResponseDto> recommend(String id, RecommendRequestDto dto);
    
    
}
