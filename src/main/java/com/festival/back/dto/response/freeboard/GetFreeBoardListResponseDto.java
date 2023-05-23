package com.festival.back.dto.response.freeboard;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.FreeBoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetFreeBoardListResponseDto {
    private int boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardImgUrl;
    private String boardWriteDatetime;
    private int viewCount;
    private int recommendCount;
    private int commentCount;
    private String writerUserId;
    private String writerProfileUrl;
    private String writerNickname;
    
    public GetFreeBoardListResponseDto(FreeBoardEntity freeBoardEntity){
        this.boardNumber = freeBoardEntity.getBoardNumber();
        this.boardTitle = freeBoardEntity.getBoardTitle();
        this.boardContent = freeBoardEntity.getBoardContent();
        this.boardImgUrl = freeBoardEntity.getBoardImgUrl();
        this.boardWriteDatetime = freeBoardEntity.getBoardWriteDatetime();
        this.writerUserId = freeBoardEntity.getWriterUserId();
        this.writerProfileUrl = freeBoardEntity.getWriterProfileUrl();
        this.writerNickname = freeBoardEntity.getWriterNickname();
        this.viewCount = freeBoardEntity.getViewCount();
        this.recommendCount = freeBoardEntity.getRecommendCount();
        this.commentCount = freeBoardEntity.getCommentCount();
    }

    public static List<GetFreeBoardListResponseDto> copyList (List<FreeBoardEntity> freeBoardEntityList) {
        List<GetFreeBoardListResponseDto> list = new ArrayList<>();

        for (FreeBoardEntity freeBoardList :freeBoardEntityList) {
            GetFreeBoardListResponseDto dto = new GetFreeBoardListResponseDto(freeBoardList);
            list.add(dto);
        }
        return list;
    }
}
