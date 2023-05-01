package com.festival.back.dto.response.festival;

import java.util.List;

import com.festival.back.entity.FestivalEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetSearchFestivalListResponseDto {
    private List<FestivalEntity> festivalList;

    
}
