package com.festival.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String writerId;
    private String writerProfileUrl;
    private String writerNickname;
    private int festivalNumber;

    public void increaseRecommendCount(){
        this.recommendCount++;
    }

    public void decreaseRecommendCount(){
        this.recommendCount--;
    }
}
