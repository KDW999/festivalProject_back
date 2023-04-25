package com.festival.back.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festival.back.common.constant.ApiPattern;

import com.festival.back.dto.request.board.PatchCommentRequestDto;

import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.request.board.RecommendRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.board.GetFestivalReviewBoardListResponseDto;
import com.festival.back.dto.response.board.GetFestivalReviewBoardResponseDto;
import com.festival.back.dto.response.board.DeleteCommentResponseDto;
import com.festival.back.dto.response.board.PatchCommentResponseDto;
import com.festival.back.dto.response.board.PostCommentResponseDto;
import com.festival.back.dto.response.board.RecommendResponseDto;
import com.festival.back.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestParam;



import com.festival.back.dto.request.board.PostReviewBoardRequestDto;
import com.festival.back.dto.response.board.PostFestivalReviewBoardResponseDto;

@Api(description="게시글 모듈")

@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {

    @Autowired private BoardService boardService;

    private final String RECOMMEND = "/recommend";
    private final String POST_COMMENT = "/post-comment/{commentNumber}";
    private final String POST_FESTIVAL_REVIEW_BOARD = "";

    private final String GET_FESTIVAL_REVIEW_BOARD="/{festivalNumber}/{boardNumber}";
    private final String GET_FESTIVAL="/{festivalNumber}";

    private final String PATCH_COMMENT = "/patch-comment";

    private final String DELETE_COMMENT = "/delete-comment/{commentNumber}";

    //? 댓글 작성
    @ApiOperation(value="댓글 작성", notes="Request Header Authorization에 Bearer JWT를 포함하고 Request Body에 boardNumber, content를 포함하여 요청을 하면, 성공시 게시물 전체 데이터를 반환, 실패시 실패 메세지를 반환")
    @PostMapping(POST_COMMENT)
    public ResponseDto<PostCommentResponseDto> postComment(
        @ApiParam(hidden=true)
        @AuthenticationPrincipal String userId,
        @Valid @RequestBody PostCommentRequestDto requestBody
    ) {
        ResponseDto<PostCommentResponseDto> response = boardService.postComment(userId, requestBody);
        return response;
    }

    //? 댓글 수정
    @ApiOperation(value="댓글 수정", notes="게시물 번호, 게시물 내용을 전송하면 게시물 작성 결과로 작성된 게시물 정보를 반환, 실패시 실패 메시지를 반환")
    @PatchMapping(PATCH_COMMENT)
    public ResponseDto<PatchCommentResponseDto> patchComment(
        @ApiParam(hidden=true)
        @AuthenticationPrincipal String userId,
        @Valid @RequestBody PatchCommentRequestDto requestBody
    ) {
        ResponseDto<PatchCommentResponseDto> response = boardService.patchComment(userId, requestBody);
        return response;
    }

    //? 댓글 삭제
    @ApiOperation(value="댓글 삭제", notes="Request Header Authorization에 Bearer JWT를 포함하고 Path Variable에 boardNumer를 포함해 요청하면, 성공시 true를 반환, 실패시 false를 반환")
    @DeleteMapping(DELETE_COMMENT)
    public ResponseDto<DeleteCommentResponseDto> deleteComment(
        @ApiParam(hidden=true)
        @AuthenticationPrincipal String userId,
        @ApiParam(value="댓글 번호", example="1", required=true)
        @PathVariable("commentNumber") int commentNumber
    ) {
        ResponseDto<DeleteCommentResponseDto> response = boardService.deleteComment(userId, commentNumber);
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
    @GetMapping(value={GET_FESTIVAL_REVIEW_BOARD,GET_FESTIVAL})
        public ResponseDto<GetFestivalReviewBoardResponseDto> getFestivalReviewBoard(@PathVariable("festivalNumber")int festivalNumber,@PathVariable(name="boardNumber") Integer boardNumber){
            ResponseDto<GetFestivalReviewBoardResponseDto> response=boardService.getFestivalReviewBoard(festivalNumber,boardNumber );
            return response;
        
    }
     // ? 특정축제 전체 후기 게시글 불러오기 -김종빈
     @GetMapping(GET_FESTIVAL)
     public ResponseDto<List<GetFestivalReviewBoardListResponseDto>> getFestivalReviewBoardList(@PathVariable("festivalNumber")Integer festivalNumber){
        ResponseDto<List<GetFestivalReviewBoardListResponseDto>> response =boardService.getFestivalReviewBoardList(festivalNumber);
        return response;
     }


    //추천 페스티벌 리스트 받아오기
    // @GetMapping(value="path")
    // public SomeData getMethodName(@RequestParam String param) {
    //     return new SomeData();
    // }
    
    

}
