package com.festival.back.service;


import java.util.List;

import com.festival.back.dto.request.freeboard.FreeBoardRecommendRequestDto;
import com.festival.back.dto.request.freeboard.PatchFreeBoardCommentRequestDto;
import com.festival.back.dto.request.freeboard.PatchFreeBoardRequestDto;
import com.festival.back.dto.request.freeboard.PostFreeBoardCommentRequestDto;
import com.festival.back.dto.request.freeboard.PostFreeBoardRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.freeboard.DeleteFreeBoardCommentResponseDto;
import com.festival.back.dto.response.freeboard.DeleteFreeBoardResponseDto;
import com.festival.back.dto.response.freeboard.FreeBoardRecommendResponseDto;
import com.festival.back.dto.response.freeboard.GetFreeBoardListResponseDto;
import com.festival.back.dto.response.freeboard.GetFreeBoardResponseDto;
import com.festival.back.dto.response.freeboard.PatchFreeBoardCommentResponseDto;
import com.festival.back.dto.response.freeboard.PatchFreeBoardResponseDto;
import com.festival.back.dto.response.freeboard.PostFreeBoardCommentResponseDto;
import com.festival.back.dto.response.freeboard.PostFreeBoardResponseDto;

public interface FreeBoardService{
    
    public ResponseDto<PostFreeBoardResponseDto> postFreeBoard(String userId, PostFreeBoardRequestDto requestBody);
    public ResponseDto<PostFreeBoardCommentResponseDto> postFreeBoardComment(String userId, PostFreeBoardCommentRequestDto requestBody);
    public ResponseDto<FreeBoardRecommendResponseDto> freeBoardRecommend(String userId, FreeBoardRecommendRequestDto requestBody);

    public ResponseDto<List<GetFreeBoardListResponseDto>> getFreeBoardList();
    public ResponseDto<GetFreeBoardResponseDto> getFreeBoard(int boardNumber);

    public ResponseDto<PatchFreeBoardResponseDto> patchFreeBoard(String userId, PatchFreeBoardRequestDto requestBody);
    public ResponseDto<PatchFreeBoardCommentResponseDto> patchFreeBoardComment(String userId, PatchFreeBoardCommentRequestDto requestBody);

    public ResponseDto<DeleteFreeBoardResponseDto> deleteFreeBoard(String userId, int boardNumber);
    public ResponseDto<DeleteFreeBoardCommentResponseDto> deleteFreeBoardComment(String userId, int boardCommentNumber);
}
