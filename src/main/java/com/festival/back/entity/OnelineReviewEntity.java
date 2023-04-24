package com.festival.back.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.festival.back.entity.primaryKey.OnelineReviewPk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Onelinereview")
@Table(name = "Onelinereview")
@IdClass(OnelineReviewPk.class)
public class OnelineReviewEntity {
    @Id
    private int festivalNumber;
    @Id
    private String userId;
    private int average;
    private String oneLineReviewContent;
    private String userProfileUrl;
    private String userNickname;

    
}
