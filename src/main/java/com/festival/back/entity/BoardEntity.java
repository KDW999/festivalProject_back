package com.festival.back.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.festival.back.dto.request.board.PatchReviewBoardRequestDto;
import com.festival.back.dto.request.board.PostReviewBoardRequestDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Board")
@Table(name = "Board")
public class BoardEntity {
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
    private String writerId;
    private String writerProfileUrl;
    private String writerNickname;
    private int festivalNumber;
    
    public BoardEntity(UserEntity userEntity,PostReviewBoardRequestDto postreviewBoardRequestDto){
        Date  now= new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.boardTitle=postreviewBoardRequestDto.getBoardTitle();
        this.boardContent=postreviewBoardRequestDto.getBoardContent();
        this.boardImgUrl=postreviewBoardRequestDto.getBoradImgUrl();
        this.boardWriteDatetime=simpleDateFormat.format(now);
        this.writerId=userEntity.getUserId();
        this.writerProfileUrl=userEntity.getProfileUrl();
        this.writerNickname=userEntity.getNickname();
        this.festivalNumber=postreviewBoardRequestDto.getFestivalNumber();
        this.viewCount=0;
        this.recommendCount=0;
        this.commentCount=0;

    }
    public void increaseCommentCount(){
        this.commentCount++;
    }

    public void decreaseCommentCount() {
        this.commentCount--;
    }
    
    public void increaseViewCount(){
        this.viewCount++;
    }

    public void increaseRecommendCount() {
        this.recommendCount++;
    }

    public void decreaseRecommendCount(){
        this.recommendCount--;
    }
    public void patch(PatchReviewBoardRequestDto dto) {
        this.boardTitle=dto.getBoardTitle();
        this.boardContent=dto.getBoardContent();
        this.boardImgUrl=dto.getBoardImgUrl();
    }
}
