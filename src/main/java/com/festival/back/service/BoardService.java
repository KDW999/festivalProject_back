package com.festival.back.service;

<<<<<<< HEAD
import com.festival.back.dto.request.board.PatchCommentRequestDto;
import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.request.board.RecommendRequestDto;
import com.festival.back.dto.response.board.PatchCommentResponseDto;
import com.festival.back.dto.response.board.PostCommentResponseDto;
import com.festival.back.dto.response.board.RecommendResponseDto;

import com.festival.back.dto.response.ResponseDto;

=======
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.request.board.GetFestivalReviewBoardReqeustDto;
import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.request.board.PostReviewBoardRequestDto;
import com.festival.back.dto.request.board.RecommendRequestDto;
import com.festival.back.dto.response.board.GetFestivalReviewBoardListResponseDto;
import com.festival.back.dto.response.board.GetFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.board.PostCommentResponseDto;
import com.festival.back.dto.response.board.PostFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.board.RecommendResponseDto;

>>>>>>> 9628635336e09a5b805ee9ae98e0286bf9f6d77c
public interface BoardService {


    public ResponseDto<PostCommentResponseDto> postComment(String userId, PostCommentRequestDto dto);
<<<<<<< HEAD
    public ResponseDto<PatchCommentResponseDto> patchComment(String userId, PatchCommentRequestDto dto);
    // public ResponseDto<PostFestivalReviewBoardResponseDto> postReviewBoard(PostReviewBoard dto);
=======
    
>>>>>>> 9628635336e09a5b805ee9ae98e0286bf9f6d77c
    public ResponseDto<RecommendResponseDto> recommend(String id, RecommendRequestDto dto);

    public ResponseDto<PostFestivalReviewBoardResponseDto> postFestivalReviewBoard(String userId,PostReviewBoardRequestDto dto);

    public ResponseDto<GetFestivalReviewBoardResponseDto> getFestivalReviewBoard(int boardNumber,Integer festivalNumber);

    public ResponseDto<GetFestivalReviewBoardListResponseDto> getFestivalReviewBoardList(int festivalNumber);

    
}
