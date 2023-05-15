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
public class GetFestivalTypeListResponseDto {
    private String festivalType;

    public GetFestivalTypeListResponseDto (FestivalEntity festivalEntity) {
        this.festivalType = festivalEntity.getFestivalType();
    }

    public static List<GetFestivalTypeListResponseDto> copyList(List<FestivalEntity> festivalEntityList) {
        
        List<GetFestivalTypeListResponseDto> list = new ArrayList<>();

        for (FestivalEntity festivalEntity: festivalEntityList) {
            GetFestivalTypeListResponseDto dto = new GetFestivalTypeListResponseDto(festivalEntity);
            list.add(dto);
        }
        return list;
    }
}
