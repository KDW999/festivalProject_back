package com.festival.back.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.BoardEntity;
import com.festival.back.entity.FestivalEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "특정 축제에 전체 후기 게시글 LIST 반환 Response ")
public class GetFestivalReviewBoardListResponseDto {

  
    @ApiModelProperty(value = "축제 Entity",required = true)
    private FestivalEntity festival;
    @ApiModelProperty(value = "boardEntity List 형태",required = true)
    private List<BoardEntity> board;
   
    
}
