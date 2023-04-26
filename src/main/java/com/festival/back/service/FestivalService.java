package com.festival.back.service;

import com.festival.back.dto.request.board.PostFestivalRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.board.PostFestivalResponseDto;

public interface FestivalService {
    public ResponseDto<PostFestivalResponseDto> postFestival(String festivalName, PostFestivalRequestDto dto);
}
