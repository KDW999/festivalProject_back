package com.festival.back.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class RecommendPk implements Serializable{
    @Column(name = "user_id")
    private String userId;
    @Column(name = "board_number")
    private int boardNumber;
    
}
