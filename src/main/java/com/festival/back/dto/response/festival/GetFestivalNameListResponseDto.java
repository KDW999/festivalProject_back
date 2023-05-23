package com.festival.back.dto.response.festival;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.FestivalEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetFestivalNameListResponseDto {

    private String festivalName;
    private int festivalNumber;

    public GetFestivalNameListResponseDto (FestivalEntity festivalEntity) {
        this.festivalName = festivalEntity.getFestivalName();
        this.festivalNumber = festivalEntity.getFestivalNumber();
    
    }

    public static List<GetFestivalNameListResponseDto> copyList(List<FestivalEntity> festivalEntityList) {
        
        List<GetFestivalNameListResponseDto> list = new ArrayList<>();

        for(FestivalEntity festivalEntity : festivalEntityList){
            String festivalName = festivalEntity.getFestivalName();
            int festivalNumber = festivalEntity.getFestivalNumber();
            GetFestivalNameListResponseDto item = new GetFestivalNameListResponseDto(festivalName, festivalNumber);
            list.add(item);
        }
        return list;
    }
}