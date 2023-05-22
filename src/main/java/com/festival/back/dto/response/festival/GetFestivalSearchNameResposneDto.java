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
public class GetFestivalSearchNameResposneDto {
    
    private String festivalName;
    private int festivalNumber;

    public GetFestivalSearchNameResposneDto (FestivalEntity festivalEntity){
     
        this.festivalName = festivalEntity.getFestivalName();
        this.festivalNumber = festivalEntity.getFestivalNumber();
    
      }

      public static List<GetFestivalSearchNameResposneDto> copyList(List<FestivalEntity> festivalEntityList){
        
        List<GetFestivalSearchNameResposneDto> list = new ArrayList<>();

        for(FestivalEntity festivalEntity : festivalEntityList){
            String festivalName = festivalEntity.getFestivalName();
            int festivalNumber = festivalEntity.getFestivalNumber();
            GetFestivalSearchNameResposneDto item = new GetFestivalSearchNameResposneDto(festivalName, festivalNumber);
            list.add(item);
        }
        return list;
    }
    
}

    

