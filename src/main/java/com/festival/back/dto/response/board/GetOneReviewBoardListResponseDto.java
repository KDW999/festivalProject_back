package com.festival.back.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.BoardEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "특정 축제 전체 후기 게시글 LIST Response")
public class GetOneReviewBoardListResponseDto {
    @ApiModelProperty(value = "후기 게시글 번호",example = "1",required = true)
    private int boardNumber;
    @ApiModelProperty(value = "후기 게시글 제목",example = "빙어가 없어요",required = true)
    private String boardTitle;
    @ApiModelProperty(value = "후기 게시글 내용",example = "빙어가 너무 없어요",required = true)
    private String boardContent;
    @ApiModelProperty(value = "후기 게시글 첨부된 이미지",example = "http:// url",required = true)
    private String boardImgUrl;
    @ApiModelProperty(value = "작성된 날자+시간",example = "2023-05-10 17:00",required = true)
    private String boardWriteDatetime;
    @ApiModelProperty(value = "조회수",example = "0",required = true)
    private int viewCount;
    @ApiModelProperty(value = "추천수",example = "0",required = true)
    private int recommendCount;
    @ApiModelProperty(value = "댓글수",example = "0",required = true)
    private int commentCount;
    @ApiModelProperty(value = "작성자 아이디",example = "karurna",required = true)
    private String writerUserId;
    @ApiModelProperty(value = "작성자 프로필 사진",example = "http:// url",required = true)
    private String writerProfileUrl;
    @ApiModelProperty(value = "작성자 닉네임",example = "혼자가아님",required = true)
    private String writerNickname;
    @ApiModelProperty(value = "작성자 휴대폰 번호",example = "010-9159-3089",required = true)
    private int festivalNumber;

    public GetOneReviewBoardListResponseDto(BoardEntity boardEntity){
        this.boardNumber=boardEntity.getBoardNumber();
        this.boardTitle=boardEntity.getBoardTitle();
        this.boardContent=boardEntity.getBoardContent();
        this.boardImgUrl=boardEntity.getBoardImgUrl();
        this.boardWriteDatetime=boardEntity.getBoardWriteDatetime();
        this.viewCount=boardEntity.getViewCount();
        this.recommendCount=boardEntity.getRecommendCount();
        this.commentCount=boardEntity.getCommentCount();
        this.writerUserId=boardEntity.getWriterUserId();
        this.writerProfileUrl=boardEntity.getWriterNickname();
        this.writerNickname=boardEntity.getWriterNickname();
        this.festivalNumber=boardEntity.getFestivalNumber();
    
    }
    public static List<GetOneReviewBoardListResponseDto> copyList(List<BoardEntity> boardEntityList) {
        List<GetOneReviewBoardListResponseDto> list = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntityList) {
            GetOneReviewBoardListResponseDto dto = new GetOneReviewBoardListResponseDto(boardEntity);
            list.add(dto);
        }
        return list;
    }

}