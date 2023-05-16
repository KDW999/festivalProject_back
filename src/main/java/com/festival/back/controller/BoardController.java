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
import com.festival.back.dto.request.board.PatchReviewBoardRequestDto;
import com.festival.back.dto.request.board.PostCommentRequestDto;
import com.festival.back.dto.request.board.RecommendReviewBoardRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.board.GetReviewBoardResponseDto;
import com.festival.back.dto.response.board.GetInterestedFestivalListResponseDto;
import com.festival.back.dto.response.board.GetMyReviewBoardListResponseDto;
import com.festival.back.dto.response.board.GetOneReviewBoardListResponseDto;
import com.festival.back.dto.response.board.GetAllReviewBoardListResponseDto;
import com.festival.back.dto.response.board.DeleteCommentResponseDto;
import com.festival.back.dto.response.board.DeleteReviewBoardResponseDto;
import com.festival.back.dto.response.board.PatchCommentResponseDto;
import com.festival.back.dto.response.board.PatchReviewBoardResponseDto;
import com.festival.back.dto.response.board.PostCommentResponseDto;
import com.festival.back.dto.response.board.RecommendReviewBoardResponseDto;
import com.festival.back.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.festival.back.dto.request.board.PostReviewBoardRequestDto;
import com.festival.back.dto.response.board.PostReviewBoardResponseDto;

@Api(description="게시글 모듈")
@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {

    @Autowired private BoardService boardService;

    private final String POST_RECOMMEND = "/recommend";
    private final String POST_COMMENT = "/post-comment";
    private final String POST_FESTIVAL_REVIEW_BOARD = "";
    private final String PATCH_FESTIVAL_REVIEW_BOARD = "";
    private final String DELETE_BOARD = "/{boardNumber}";
    private final String GET_MY_LIST = "/my-reviewboard-list";
    private final String GET_ALL_REVIEWBOARD_LIST="/all-review-board";
    private final String GET_FESTIVAL_REVIEW_LIST="/festival/{festivalNumber}";
    
    private final String GET_INTERESTED_FESTIVAL_LIST = "/festival/interested-list";
    private final String GET_ONLY_FESTIVAL_LIST="/onlyfestival/{festivalNumber}";

    private final String GET_FESTIVAL_REVIEW_BOARD="/{boardNumber}";
 

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
    @ApiOperation(value="댓글 수정", notes="Request Header Authorization에 Bearer JWT를 포함하고 Request Body에 boardNumber, commentNumber, commentContent를 전송하면 게시물 작성 결과로 작성된 게시물 정보를 반환, 실패시 실패 메시지를 반환")
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
    @ApiOperation(value="댓글 삭제", notes="Request Header Authorization에 Bearer JWT를 포함하고 Path Variable에 commentNumber를 포함해 요청하면, 성공시 true를 반환, 실패시 false를 반환")
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

    //? 후기 게시물 추천하기
    @ApiOperation(value = "추천 기능", notes = "Request Header Authorization에 Bearer JWT를 포함하고 " +
    "Request Body에 boardNumber를 포함하여 요청을 하면, 성공 시 게시물 전체 데이터를 반환")
    @PostMapping(POST_RECOMMEND)
    public ResponseDto<RecommendReviewBoardResponseDto> 
    recommend(@ApiParam(hidden = true) 
    @AuthenticationPrincipal String userId, @Valid @RequestBody RecommendReviewBoardRequestDto requestBody){
        ResponseDto<RecommendReviewBoardResponseDto> response = boardService.recommend(userId, requestBody);
        return response;       
    }

    // ? 축제 후기 게시물 작성 -김종빈
    @ApiOperation(value = " 축제 후기 게시글 작성",notes = "Request Header Authorization에 Bearer JWT를 포함하고 "+
    "Request Bdody 에 작성할 festivalNumber 과 boardTitle boardContent boardimgurl 을 작성하면 게시물 전제 테이터 바환." )
    @PostMapping(POST_FESTIVAL_REVIEW_BOARD)
    public ResponseDto<PostReviewBoardResponseDto> 
    postFestivalReviewBoard(@AuthenticationPrincipal String userId,
    @Valid @RequestBody PostReviewBoardRequestDto requestbody){
        ResponseDto<PostReviewBoardResponseDto> 
        response=boardService.postReviewBoard(userId,requestbody);
        return response; 
    }

    // ? 특정 축제 특정 후기 게시글 불러오기 -김종빈
    @ApiOperation(value = "특정 축제를 불러와 그에 관한 후기 게시글 1개를 반환한다."
    ,notes = "특정 축제 festivalNumber 과 boardNumber 을 pathvariable 로 받아서 보내면 축제정보 와 게시물을 반환하고 실패 시 실폐 메세지 반환. ")
    @GetMapping(GET_FESTIVAL_REVIEW_BOARD)
        public ResponseDto<GetReviewBoardResponseDto> getFestivalReviewBoard(@PathVariable(name="boardNumber") Integer boardNumber){
            ResponseDto<GetReviewBoardResponseDto> response=boardService.getReviewBoard(boardNumber);
            return response;
    }
    
    //  ?특정 축제 후기 수정하기 -김종빈
    @ApiOperation(value = "특정 축제 특정 후기 게시글 수정한다.",
    notes = "Request Header Authorization에 Bearer JWT를 포함하고 필요정보를 받고 성공하면 게시글이 수정되고 실패하면 실패 메세지 반환")
    @PatchMapping(PATCH_FESTIVAL_REVIEW_BOARD)
    public ResponseDto<PatchReviewBoardResponseDto> patchReivewBoard(@AuthenticationPrincipal String userId,@Valid @RequestBody PatchReviewBoardRequestDto requestBody){
        ResponseDto<PatchReviewBoardResponseDto> response =boardService.patchReivewBoard(userId, requestBody);
        return response;
    } 

    // ? 특정 게시물 삭제-김종빈
    @ApiOperation(value = "축제 후기 게시물 삭제"
    ,notes = "Reques Header 에 Athorization 에 Bearer JWT 를 포함하고 pathvariable 에 boardNumber 를 포함하여 반환하면"+
    "성공시 전체 게시물 데이터를 반환하고,실패시 실패 메세지를 반환")
    @DeleteMapping(DELETE_BOARD)
    public ResponseDto<DeleteReviewBoardResponseDto> deleteBoard(
        @ApiParam(hidden = true)
        @AuthenticationPrincipal String email,
    @ApiParam(value = "게시물 번호",example = "1",required = true)
    @PathVariable("boardNumber")int boardNumber){
        ResponseDto<DeleteReviewBoardResponseDto> response = boardService.deleteBoard(email,boardNumber);
        return response;
    }
    
// ? 본인이 작성한 전체리스트 불러오기-김종빈
    @ApiOperation(value = "본인 작성 게시물 리스트 가져오기"
    ,notes = "Request Header 에 Athorization 에 Bearer JWT 를 포함하여 요청하면 성공시 요청자가 게시물 ")
    @GetMapping(GET_MY_LIST)
    public ResponseDto<List<GetMyReviewBoardListResponseDto>> getMyList(@ApiParam(hidden = true)@AuthenticationPrincipal String userId){
        ResponseDto<List<GetMyReviewBoardListResponseDto>> response= boardService.getMyList(userId);
        return response;
    }

    // ? 추천 페스티벌 리스트 받아오기 -감재현
    @ApiOperation(value="회원가입시 선택한 추천 축제 타입 리스트 받아오기")
    @GetMapping(GET_INTERESTED_FESTIVAL_LIST)
    public ResponseDto<List<GetInterestedFestivalListResponseDto>> GetInterestedFestivalList(@ApiParam(hidden = true) @AuthenticationPrincipal String userId) {
        ResponseDto<List<GetInterestedFestivalListResponseDto>> response = boardService.getInterestedFestivalList(userId);
        return response;
    }
    // ? 특정 후기 만 전체 반환.
    @ApiOperation(value = "특정 축제 후기 만 전체 반환 한다.")
    @GetMapping(GET_ONLY_FESTIVAL_LIST)
    public ResponseDto<List<GetOneReviewBoardListResponseDto>> getOneFestivalReviewBoard(@PathVariable("festivalNumber")int festivalNumber){
        ResponseDto<List<GetOneReviewBoardListResponseDto>> response = boardService.getOneFestivalReviewBoard(festivalNumber);
        return response;
    }

    //? 전체 후기 게시물 리스트 
    @ApiOperation(value = "전체 후기 게시글 리스트 반환")
    @GetMapping(GET_ALL_REVIEWBOARD_LIST)
    public ResponseDto<List<GetAllReviewBoardListResponseDto>> getAllReviewBoardList(){
        ResponseDto<List<GetAllReviewBoardListResponseDto>> response = boardService.getAllReviewBoardList();
        return response;

    }
    
}
