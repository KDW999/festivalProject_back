package com.festival.back.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festival.back.common.constant.ApiPattern;
import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.request.board.RecommendRequestDto;
import com.festival.back.dto.request.board.PostReviewBoardRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.board.GetFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.board.PostCommentResponseDto;
import com.festival.back.dto.response.board.PostFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.board.RecommendResponseDto;
import com.festival.back.service.BoardService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {
    @Autowired BoardService boardService;

    private final String POST_FESTIVAL_REVIEW_BOARD = "";
    private final String RECOMMEND = "/recommend";
    private final String POST_COMMENT = "/comment";

    private final String GET_FESTIVAL_REVIEW_BOARD="/{festivalNumber}/{boardNumber}";
    private final String GET_FESTIVAL_REVIEW="/{festivalNumber}";
    private final String GET_FESTIVAL_REVIEW="/{festivalNumber}";


    //? 글 댓글 달기
    @PostMapping(POST_COMMENT)
    public ResponseDto<PostCommentResponseDto> 
    postComment(@AuthenticationPrincipal String id, @Valid @RequestBody PostCommentRequestDto requestBody ) {
        ResponseDto<PostCommentResponseDto> response = boardService.postComment(id, requestBody);
        return response;
    }
    
    
    //? 글 추천하기
    @ApiOperation(value = "추천 기능", notes = "Request Header Authorization에 Bearer JWT를 포함하고 " +
    "Request Body에 boardNumber를 포함하여 요청을 하면, 성공 시 게시물 전체 데이터를 반환")
    @PostMapping(RECOMMEND)
    public ResponseDto<RecommendResponseDto> 
    recommend(@ApiParam(hidden = true) 
    @AuthenticationPrincipal String userId, @Valid @RequestBody RecommendRequestDto requestBody){
        ResponseDto<RecommendResponseDto> response = boardService.recommend(userId, requestBody);
        return response;       
    }

    // ? 축제 후기 게시판 작성 -김종빈
    @PostMapping(POST_FESTIVAL_REVIEW_BOARD)
    public ResponseDto<PostFestivalReviewBoardResponseDto> 
    postFestivalReviewBoard(@AuthenticationPrincipal String userId,
    @Valid @RequestBody PostReviewBoardRequestDto requestbody){
        ResponseDto<PostFestivalReviewBoardResponseDto> 
        response=boardService.postFestivalReviewBoard(userId,requestbody);
        return response;
        
    }

    // ? 특정 축제 특정 후기 게시글 불러오기 -김종빈
    @GetMapping(value={GET_FESTIVAL_REVIEW_BOARD,GET_FESTIVAL_REVIEW})
        public ResponseDto<GetFestivalReviewBoardResponseDto> getFestivalReviewBoard(@PathVariable("festivalNumber")int festivalNumber,@PathVariable(name="boardNumber") Integer boardNumber){
            ResponseDto<GetFestivalReviewBoardResponseDto> response=boardService.getFestivalReviewBoard(festivalNumber,boardNumber );
            return response;
        
    }

    //추천 페스티벌 리스트 받아오기
    @GetMapping(value="path")
    public SomeData getMethodName(@RequestParam String param) {
        return new SomeData();
    }
    
    

}
