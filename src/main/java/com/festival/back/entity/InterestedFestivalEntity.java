package com.festival.back.entity;

import java.util.ArrayList;
import java.util.List;

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
@Entity(name = "Interestedfestival")
@Table(name = "Interestedfestival")
public class InterestedFestivalEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence;
    private String userId;
    private String interestedFestivalType;

    public InterestedFestivalEntity(String userId, String interestedFestivalType) {
        this.userId = userId;
        this.interestedFestivalType = interestedFestivalType;
    }

    public static List<InterestedFestivalEntity> createList(SignUpRequestDto dto) {
        String userId = dto.getUserId();
        List<String> list = dto.getInterestedFestival();
        List<InterestedFestivalEntity> result = new ArrayList<>();

        for (String interestedFestival: list) {
            InterestedFestivalEntity entity = new InterestedFestivalEntity(userId, interestedFestival);
            result.add(entity);
        }
        return result;
    }
}