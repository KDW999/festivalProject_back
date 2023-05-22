package com.festival.back.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.BoardEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSearchReviewBoardListResponseDto {
    @ApiModelProperty(value = "게시물 번호", example = "1", required = true)
    private int boardNumber;
    @ApiModelProperty(value = "게시물 제목", example = "빙어 좋아요", required = true)
    private String boardTitle;
    @ApiModelProperty(value = "게시물 내용", example = "빙어가 없어요", required = true)
    private String boardContent;
    @ApiModelProperty(value = " 게시글 첨부 이미지", example = "IMG HTTP URL", required = true)
    private String boardImgUrl;
    @ApiModelProperty(value = "게시물 작성시간", example = "2023-04-25 14:00", required = true)
    private String boardWriteDatetime;
    @ApiModelProperty(value = "게시물 조회수", example = "1", required = true)
    private int viewCount;
    @ApiModelProperty(value = "게시물 추천수", example = "1", required = true)
    private int recommendCount;
    @ApiModelProperty(value = "게시물 댓글 수", example = "1", required = true)
    private int commentCount;
    @ApiModelProperty(value = "게시물 작성자 아이디", example = "karurana", required = true)
    private String writerUserId;
    @ApiModelProperty(value = "게시물 작성자 프로필 사진", example = "1", required = true)
    private String writerProfileUrl;
    @ApiModelProperty(value = "게시물 번호", example = "1IMG HTTP URL", required = true)
    private String writerNickname;
    @ApiModelProperty(value = "축제 게시물 번호", example = "1", required = true)
    private int festivalNumber;

    public GetSearchReviewBoardListResponseDto(BoardEntity boardEntity) {
        this.boardNumber = boardEntity.getBoardNumber();
        this.boardTitle = boardEntity.getBoardTitle();
        this.boardContent = boardEntity.getBoardContent();
        this.boardImgUrl = boardEntity.getBoardImgUrl();
        this.boardWriteDatetime = boardEntity.getBoardWriteDatetime();
        this.viewCount = boardEntity.getViewCount();
        this.recommendCount = boardEntity.getRecommendCount();
        this.writerUserId = boardEntity.getWriterUserId();
        this.writerProfileUrl = boardEntity.getWriterProfileUrl();
        this.writerNickname = boardEntity.getWriterNickname();
        this.festivalNumber = boardEntity.getFestivalNumber();
    }

    public static List<GetSearchReviewBoardListResponseDto> copyList(List<BoardEntity> boardEntityList) {
        List<GetSearchReviewBoardListResponseDto> list = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntityList) {
            GetSearchReviewBoardListResponseDto dto = new GetSearchReviewBoardListResponseDto(boardEntity);
            list.add(dto);
        }
        return list;
    }
}


