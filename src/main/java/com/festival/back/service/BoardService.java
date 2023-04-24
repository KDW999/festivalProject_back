package com.festival.back.service;

import com.festival.back.dto.request.PostReviewBoardRequestDto;
import com.festival.back.dto.response.PostFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.ResponseDto;

public interface BoardService {


    public ResponseDto<PostFestivalReviewBoardResponseDto> postReviewBoard(PostReviewBoardRequestDto dto,String userId);

    
}
