package com.festival.back.service;


import com.festival.back.dto.request.freeboard.PostFreeBoardRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.freeboard.PostFreeBoardResponseDto;

public interface FreeBoardService{
    
    public ResponseDto<PostFreeBoardResponseDto> postFreeBoard(String userId, PostFreeBoardRequestDto requestBody);
}
