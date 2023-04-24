package com.festival.back.service;

import com.festival.back.dto.response.ResponseDto;

public interface BoardService {


    public ResponseDto<PostFestivalReviewBoardResponseDto> postReviewBoard(PostReviewBoard dto);

    
}
