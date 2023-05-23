package com.festival.back.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.festival.back.dto.request.freeboard.PatchFreeBoardRequestDto;
import com.festival.back.dto.request.freeboard.PostFreeBoardRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Freeboard")
@Table(name = "Freeboard")
public class FreeBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    
    public FreeBoardEntity(UserEntity userEntity, PostFreeBoardRequestDto postFreeBoardRequestDto){
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.boardTitle = postFreeBoardRequestDto.getBoardTitle();
        this.boardContent = postFreeBoardRequestDto.getBoardContent();
        this.boardImgUrl = postFreeBoardRequestDto.getBoardImgUrl();
        this.boardWriteDatetime = simpleDateFormat.format(now);
        this.writerUserId = userEntity.getUserId();
        this.writerProfileUrl = userEntity.getProfileUrl();
        this.writerNickname = userEntity.getNickname();
        this.viewCount = 0;
        this.recommendCount = 0;
        this.commentCount = 0;
    }

    public void patch(PatchFreeBoardRequestDto dto) {
        this.boardTitle=dto.getBoardTitle();
        this.boardContent=dto.getBoardContent();
        this.boardImgUrl=dto.getBoardImgUrl();
    }

    public void increaseCommentCount() {
        commentCount++;
    }

    public void decreaseCommentCount() {
        commentCount--;
    }

    public void increaseRecommendCount() {
        recommendCount++;
    }

    public void decreaseRecommendCount() {
        recommendCount--;
    }

    public void increaseViewCount() {
        viewCount++;
    }
}