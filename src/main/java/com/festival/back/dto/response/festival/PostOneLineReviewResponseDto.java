package com.festival.back.dto.response.festival;

import java.util.List;

import com.festival.back.entity.FestivalEntity;
import com.festival.back.entity.OneLineReviewEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "한 줄 평 작성 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOneLineReviewResponseDto {

    @ApiModelProperty(value = "축제 정보 Entity", required = true)
    private FestivalEntity festival;

    @ApiModelProperty(value = "한 줄 평 Entity List", required = true)
    private List<OneLineReviewEntity> oneLineReviewList;
}