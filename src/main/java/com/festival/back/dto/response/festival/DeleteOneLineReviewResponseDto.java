package com.festival.back.dto.response.festival;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "특정 축제 게시물의 한 줄 평 삭제 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOneLineReviewResponseDto {
    
    @ApiModelProperty(value = "특정 한 줄 평 삭제 결과", example = "true", required = true)
    private boolean resultStatus;
}