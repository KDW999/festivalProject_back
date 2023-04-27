package com.festival.back.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.BoardEntity;
import com.festival.back.entity.FestivalEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetFestivalReviewBoardListResponseDto {

  
    @ApiModelProperty(value = "축제 Entity",example = "1",required = true)
    private FestivalEntity festival;
    private List<BoardEntity> board;
   
    
}
