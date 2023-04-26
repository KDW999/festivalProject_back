package com.festival.back.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.FestivalEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetInterestedFestivalListResponseDto {

    private int festivalNumber;
    private String festivalName;
    private String festivalType;
    private String festivalDurationStart;
    private String festivalDurationEnd;
    private String festivalTime;
    private String festivalArea;
    private String festivalCost;

    public GetInterestedFestivalListResponseDto (FestivalEntity festivalEntity) {
        this.festivalNumber = festivalEntity.getFestivalNumber();
        this.festivalName = festivalEntity.getFestivalName();
        this.festivalType = festivalEntity.getFestivalType();
        this.festivalDurationStart = festivalEntity.getFestivalDurationStart();
        this.festivalDurationEnd = festivalEntity.getFestivalDurationEnd();
        this.festivalTime = festivalEntity.getFestivalTime();
        this.festivalArea = festivalEntity.getFestivalArea();
        this.festivalCost = festivalEntity.getFestivalCost();
    }

    public static List<GetInterestedFestivalListResponseDto> copyList(List<FestivalEntity> festivaleEntityList) {
        List<GetInterestedFestivalListResponseDto> list = new ArrayList<>();

        for (FestivalEntity festivalEntity : festivaleEntityList) {
            GetInterestedFestivalListResponseDto dto = new GetInterestedFestivalListResponseDto(festivalEntity);
            list.add(dto);
        }

        return list;
    }
}
