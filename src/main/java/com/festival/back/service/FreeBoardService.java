package com.festival.back.service;


import com.festival.back.dto.request.freeboard.PatchFreeBoardRequestDto;
import com.festival.back.dto.request.freeboard.PostFreeBoardRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.freeboard.PatchFreeBoardResponseDto;
import com.festival.back.dto.response.freeboard.PostFreeBoardResponseDto;

public interface FreeBoardService{
    
    public ResponseDto<PostFreeBoardResponseDto> postFreeBoard(String userId, PostFreeBoardRequestDto requestBody);
    public ResponseDto<PatchFreeBoardResponseDto> patchFreeBoard(String userId, PatchFreeBoardRequestDto requestBody);
}
