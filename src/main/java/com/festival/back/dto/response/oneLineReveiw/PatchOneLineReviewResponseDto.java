package com.festival.back.dto.response.oneLineReveiw;

import java.util.List;

import com.festival.back.entity.FestivalEntity;
import com.festival.back.entity.OneLineReviewEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "특정 축제 게시물의 한 줄 평 수정 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchOneLineReviewResponseDto {
    
    @ApiModelProperty(value = "축제 정보 Entity", required = true)
    private FestivalEntity festival;

    @ApiModelProperty(value = "한 줄 평 Entity List", required = true)
    private List<OneLineReviewEntity> oneLineReviewList;
}
