package com.festival.back.dto.response.freeboard;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.FreeBoardEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSearchFreeBoardListResponseDto {
    
    @ApiModelProperty(value = "게시물 번호", example = "1", required = true)
    private int boardNumber;
    @ApiModelProperty(value = "게시물 제목", example = "개인적으로 벚꽃 축제 좋은 것 같아요", required = true)
    private String boardTitle;
    @ApiModelProperty(value = "게시물 내용", example = "벚꽃이 엄청 많이 피더라고요", required = true)
    private String boardContent;
    @ApiModelProperty(value = " 게시글 첨부 이미지", example = "http://~", required = true)
    private String boardImgUrl;
    @ApiModelProperty(value = "게시물 작성 시간", example = "2023-04-25 14:00", required = true)
    private String boardWriteDatetime;
    @ApiModelProperty(value = "게시물 조회수", example = "1", required = true)
    private int viewCount;
    @ApiModelProperty(value = "게시물 추천수", example = "1", required = true)
    private int recommendCount;
    @ApiModelProperty(value = "게시물 댓글 수", example = "1", required = true)
    private int commentCount;
    @ApiModelProperty(value = "게시물 작성자 아이디", example = "kdw22222", required = true)
    private String writerUserId;
    @ApiModelProperty(value = "게시물 작성자 프로필 사진 URL", example = "1", required = true)
    private String writerProfileUrl;
    @ApiModelProperty(value = "게시물 작성자 닉네임", example = "cherryblossom", required = true)
    private String writerNickname;

    public GetSearchFreeBoardListResponseDto(FreeBoardEntity freeBoardEntity){
        this.boardNumber = freeBoardEntity.getBoardNumber();
        this.boardTitle = freeBoardEntity.getBoardTitle();
        this.boardContent = freeBoardEntity.getBoardContent();
        this.boardImgUrl = freeBoardEntity.getBoardImgUrl();
        this.boardWriteDatetime = freeBoardEntity.getBoardWriteDatetime();
        this.viewCount = freeBoardEntity.getViewCount();
        this.recommendCount = freeBoardEntity.getRecommendCount();
        this.writerUserId = freeBoardEntity.getWriterUserId();
        this.writerProfileUrl = freeBoardEntity.getWriterProfileUrl();
        this.writerNickname = freeBoardEntity.getWriterNickname();
    }

    public static List<GetSearchFreeBoardListResponseDto> copyList(List<FreeBoardEntity> freeBoardEntityList){
        List<GetSearchFreeBoardListResponseDto> list = new ArrayList<>();

        for(FreeBoardEntity freeBoardEntity : freeBoardEntityList){
            GetSearchFreeBoardListResponseDto dto = new GetSearchFreeBoardListResponseDto(freeBoardEntity);
            list.add(dto);
        }
        return list;
    }
}
