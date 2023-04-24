package com.festival.back.dto.request.board;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "추천 기능 Request Body")
@Data
@NoArgsConstructor
public class RecommendRequestDto {
    
    //? 추천하기 위해선 게시물 번호만 입력
    @ApiModelProperty(value = "게시물 번호", example = "1", required = true)
    @Min(1)
    private int boardNumber;
}
