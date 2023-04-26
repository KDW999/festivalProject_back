package com.festival.back.service;


import com.festival.back.dto.response.ResponseDto;

import java.util.List;

import com.festival.back.dto.request.board.PatchCommentRequestDto;
import com.festival.back.dto.request.board.PatchReviewBoardRequestDto;
import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.request.board.PostReviewBoardRequestDto;
import com.festival.back.dto.request.board.RecommendRequestDto;
import com.festival.back.dto.response.board.DeleteCommentResponseDto;
import com.festival.back.dto.response.board.DeleteFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.board.GetFestivalReviewBoardListResponseDto;
import com.festival.back.dto.response.board.GetFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.board.GetInterestedFestivalListResponseDto;
import com.festival.back.dto.response.board.GetMyFestivalReviewBoardListResponseDto;
import com.festival.back.dto.response.board.PatchCommentResponseDto;
import com.festival.back.dto.response.board.PatchFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.board.PostCommentResponseDto;
import com.festival.back.dto.response.board.PostFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.board.RecommendResponseDto;

public interface BoardService {

    public ResponseDto<PostCommentResponseDto> postComment(String userId, PostCommentRequestDto dto);
    public ResponseDto<PatchCommentResponseDto> patchComment(String userId, PatchCommentRequestDto dto);


    public ResponseDto<DeleteCommentResponseDto> deleteComment(String userId, int commentNumber);

    public ResponseDto<RecommendResponseDto> recommend(String id, RecommendRequestDto dto);

    public ResponseDto<PostFestivalReviewBoardResponseDto> postFestivalReviewBoard(String userId,PostReviewBoardRequestDto dto);

    public ResponseDto<GetFestivalReviewBoardResponseDto> getFestivalReviewBoard(int boardNumber,Integer festivalNumber);

    public ResponseDto<List<GetFestivalReviewBoardListResponseDto>> getFestivalReviewBoardList(Integer festivalNumber);

    public ResponseDto<PatchFestivalReviewBoardResponseDto> patchReivewBoard(String userId,PatchReviewBoardRequestDto dto);
    
    public ResponseDto<List<GetMyFestivalReviewBoardListResponseDto>> getMyList(String userId);
    
    public ResponseDto<List<GetInterestedFestivalListResponseDto>> GetInterestedFestivalList(String userId);

    public ResponseDto<DeleteFestivalReviewBoardResponseDto> deleteBoard(String userId, int boardNumber);
}
