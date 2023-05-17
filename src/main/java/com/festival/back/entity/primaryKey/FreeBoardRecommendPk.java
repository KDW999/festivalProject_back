package com.festival.back.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class FreeBoardRecommendPk implements Serializable{
    @Column(name = "user_id")
    private String userId;
    @Column(name = "free_board_number")
    private int freeBoardNumber;
}
