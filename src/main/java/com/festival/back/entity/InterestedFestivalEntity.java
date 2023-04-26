package com.festival.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.festival.back.dto.request.auth.SignUpRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "InterestedFestival")
@Table(name = "InterestedFestival")
public class InterestedFestivalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence;
    private String userId;
    private String interestedFestivalType;

    public InterestedFestivalEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.interestedFestivalType = dto.getInterestedFestival();
    }
}
