package com.festival.back.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.festival.back.entity.primaryKey.FreeBoardRecommendPk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Freeboardrecommend")
@Table(name = "Freeboardrecommend")
@IdClass(FreeBoardRecommendPk.class)
public class FreeBoardRecommendEntity {
    @Id
    private String userId;
    @Id
    private int freeBoardNumber;
    private String userProfileUrl;
    private String userNickname;

    public FreeBoardRecommendEntity(UserEntity userEntity, int freeBoardNumber) {
        this.userId = userEntity.getUserId();
        this.freeBoardNumber = freeBoardNumber;
        this.userProfileUrl = userEntity.getProfileUrl();
        this.userNickname = userEntity.getNickname();
    }
}
