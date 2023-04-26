package com.festival.back.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneLineReviewPk implements Serializable {
    @Column(name = "user_id")
    private String userId;
    @Column(name = "festival_number")
    private int festivalNumber;

    
}
