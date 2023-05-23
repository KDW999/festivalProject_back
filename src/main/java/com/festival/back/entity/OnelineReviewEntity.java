package com.festival.back.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.festival.back.dto.request.festival.PatchOneLineReviewRequestDto;
import com.festival.back.dto.request.festival.PostOneLineReviewRequestDto;
import com.festival.back.entity.primaryKey.OneLineReviewPk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Onelinereview")
@Table(name = "Onelinereview")
@IdClass(OneLineReviewPk.class)
public class OneLineReviewEntity {

    @Id
    private int festivalNumber;
    @Id
    private String userId;
    
    private int average;
    private String oneLineReviewContent;
    private String userProfileUrl;
    private String userNickname;
    private String writeDatetime;

    public OneLineReviewEntity(UserEntity userEntity, PostOneLineReviewRequestDto dto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.festivalNumber = dto.getFestivalNumber();
        this.userId = userEntity.getUserId();
        this.average = dto.getAverage();
        this.oneLineReviewContent = dto.getOneLineReviewContent();
        this.userProfileUrl = userEntity.getProfileUrl();
        this.userNickname = userEntity.getNickname();
        this.writeDatetime = simpleDateFormat.format(now);
        
    }

    public void patch(PatchOneLineReviewRequestDto dto){
        this.average = dto.getAverage();
        this.oneLineReviewContent = dto.getOneLineReviewContent();
    }
}