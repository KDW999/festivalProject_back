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
@Entity(name = "Freeboardcomment")
@Table(name = "Freeboardcomment")
public class FreeBoardCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentNumber;
    private String commentContent;
    private int boardNumber;
    private String writerId;
    private String writeDatetime;
    private String writerProfileUrl;
    private String writerNickname;

    // public CommentEntity(UserEntity userEntity, PostCommentRequestDto dto) {
    //     Date now = new Date();
    //     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //     this.commentContent = dto.getCommentContent();
    //     this.boardNumber = dto.getBoardNumber();
    //     this.writeDatetime = simpleDateFormat.format(now);
    //     this.writerId = userEntity.getUserId();
    //     this.writerProfileUrl = userEntity.getProfileUrl();
    //     this.writerNickname = userEntity.getNickname();
    // }

    // public void patch(PatchCommentRequestDto dto){
    //     this.commentContent = dto.getCommentContent();
    // }
}
