package com.festival.back.service;


import com.festival.back.dto.response.ResponseDto;

import java.util.List;

import com.festival.back.dto.request.board.PatchCommentRequestDto;
import com.festival.back.dto.request.board.PatchReviewBoardRequestDto;
import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.request.board.PostReviewBoardRequestDto;
import com.festival.back.dto.request.board.RecommendReviewBoardRequestDto;
import com.festival.back.dto.response.board.DeleteCommentResponseDto;
import com.festival.back.dto.response.board.DeleteReviewBoardResponseDto;
import com.festival.back.dto.response.board.GetReviewBoardResponseDto;
import com.festival.back.dto.response.board.GetInterestedFestivalListResponseDto;
import com.festival.back.dto.response.board.GetMyReviewBoardListResponseDto;
import com.festival.back.dto.response.board.GetOneReviewBoardListResponseDto;
import com.festival.back.dto.response.board.GetAllReviewBoardListResponseDto;
import com.festival.back.dto.response.board.GetSearchReviewBoardListResponseDto;
import com.festival.back.dto.response.board.PatchCommentResponseDto;
import com.festival.back.dto.response.board.PatchReviewBoardResponseDto;
import com.festival.back.dto.response.board.PostCommentResponseDto;
import com.festival.back.dto.response.board.PostReviewBoardResponseDto;
import com.festival.back.dto.response.board.RecommendReviewBoardResponseDto;

public interface BoardService {

    public ResponseDto<PostCommentResponseDto> postComment(String userId, PostCommentRequestDto dto);

    public ResponseDto<PatchCommentResponseDto> patchComment(String userId, PatchCommentRequestDto dto);

    public ResponseDto<DeleteCommentResponseDto> deleteComment(String userId, int commentNumber);

    public ResponseDto<RecommendReviewBoardResponseDto> recommend(String id, RecommendReviewBoardRequestDto dto);

    public ResponseDto<PostReviewBoardResponseDto> postReviewBoard(String userId,PostReviewBoardRequestDto dto);

    public ResponseDto<GetReviewBoardResponseDto> getReviewBoard(Integer boardNumber);

    public ResponseDto<PatchReviewBoardResponseDto> patchReivewBoard(String userId,PatchReviewBoardRequestDto dto);
    
    public ResponseDto<List<GetMyReviewBoardListResponseDto>> getMyList(String userId);
    
    public ResponseDto<List<GetInterestedFestivalListResponseDto>> getInterestedFestivalList(String userId);

    public ResponseDto<DeleteReviewBoardResponseDto> deleteBoard(String userId, int boardNumber);

    public ResponseDto<List<GetSearchReviewBoardListResponseDto>>  getSearchReviewBoardList(String searchWord);

    public ResponseDto<List<GetOneReviewBoardListResponseDto>> getOneFestivalReviewBoard(int festivalNumber);
    
    public ResponseDto<List<GetAllReviewBoardListResponseDto>> getAllReviewBoardList();

}