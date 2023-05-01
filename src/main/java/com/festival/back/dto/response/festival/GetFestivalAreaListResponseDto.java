package com.festival.back.dto.response.festival;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.FestivalEntity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "지역별 축제 리스트 가져오기 Response Data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetFestivalAreaListResponseDto {
    
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

    public GetFestivalAreaListResponseDto(FestivalEntity festivalEntity){
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
    }

    public static List<GetFestivalAreaListResponseDto> copyList(List<FestivalEntity> festivalEntityList){
        
        List<GetFestivalAreaListResponseDto> list = new ArrayList<>();

        for(FestivalEntity festivalEntity : festivalEntityList){
            GetFestivalAreaListResponseDto dto = new GetFestivalAreaListResponseDto(festivalEntity);
            list.add(dto);
        }

        return list;
    }
}
