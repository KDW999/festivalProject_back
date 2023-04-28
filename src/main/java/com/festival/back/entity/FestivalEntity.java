package com.festival.back.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.festival.back.dto.request.board.PostFestivalRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Festival")
@Table(name = "Festival")
public class FestivalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int festivalNumber;
    private String festivalName;
    private String festivalType;
    private String festivalDurationStart;
    private String festivalDurationEnd;
    private String festivalTime;
    private String festivalArea;
    private String festivalCost;
    private int onelineReviewAverage;
    private String festivalInformationUrl;
    private String festivalInformation;
    
    public FestivalEntity(UserEntity userEntity, PostFestivalRequestDto dto){
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        this.festivalName = dto.getFestivalName();
        this.festivalType = dto.getFestivalType();
        this.festivalDurationStart = dto.getFestivalDurationStart();
        this.festivalDurationEnd = dto.getFestivalDurationEnd();
        this.festivalTime = simpleDateFormat.format(now);
        this.festivalArea = dto.getFestivalArea();
        this.festivalCost = dto.getFestivalCost();
        this.festivalInformationUrl = dto.getFestivalInformationUrl();
        this.festivalInformation = dto.getFestivalInformation();
        
    }
    
}
