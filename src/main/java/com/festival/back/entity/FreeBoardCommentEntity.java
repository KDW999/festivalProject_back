package com.festival.back.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.festival.back.dto.request.freeboard.PatchFreeBoardCommentRequestDto;
import com.festival.back.dto.request.freeboard.PostFreeBoardCommentRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Freeboardcomment")
@Table(name = "Freeboardcomment")
public class FreeBoardCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int freeBoardCommentNumber;
    private String freeBoardCommentContent;
    private int freeBoardNumber;
    private String writerUserId;
    private String writeDatetime;
    private String writerProfileUrl;
    private String writerNickname;

    public FreeBoardCommentEntity(UserEntity userEntity, PostFreeBoardCommentRequestDto dto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        this.freeBoardCommentContent = dto.getFreeBoardCommentContent();
        this.freeBoardNumber = dto.getFreeBoardNumber();
        this.writeDatetime = simpleDateFormat.format(now);
        this.writerUserId = userEntity.getUserId();
        this.writerProfileUrl = userEntity.getProfileUrl();
        this.writerNickname = userEntity.getNickname();
    }

    public void patch(PatchFreeBoardCommentRequestDto dto) {
        this.freeBoardCommentContent = dto.getFreeBoardCommentContent();
    }
}
