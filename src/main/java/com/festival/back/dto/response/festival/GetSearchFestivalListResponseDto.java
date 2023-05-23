package com.festival.back.dto.response.festival;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.FestivalEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetSearchFestivalListResponseDto {

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
    private String festivalHomepage;

    public GetSearchFestivalListResponseDto(FestivalEntity festivalEntity){
        this.festivalNumber = festivalEntity.getFestivalNumber();
        this.festivalName = festivalEntity.getFestivalName();
        this.festivalType = festivalEntity.getFestivalType();
        this.festivalDurationStart = festivalEntity.getFestivalDurationStart();
        this.festivalDurationEnd = festivalEntity.getFestivalDurationEnd();
        this.festivalTime = festivalEntity.getFestivalTime();
        this.festivalArea = festivalEntity.getFestivalArea();
        this.festivalCost = festivalEntity.getFestivalCost();
        this.festivalInformationUrl = festivalEntity.getFestivalInformationUrl();
        this.festivalInformation = festivalEntity.getFestivalInformation();
        this.festivalHomepage=festivalEntity.getFestivalHomepage();
    }

    public static List<GetSearchFestivalListResponseDto> copyList(List<FestivalEntity> festivalEntityList){
        
        List<GetSearchFestivalListResponseDto> list = new ArrayList<>();

        for(FestivalEntity festivalEntity : festivalEntityList){
            GetSearchFestivalListResponseDto dto = new GetSearchFestivalListResponseDto(festivalEntity);
            list.add(dto);
        }
        return list;
    }
}