package com.festival.back.dto.response.board;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "특정 게시물 삭제 Response Body-data")
public class DeleteReviewBoardResponseDto {
    @ApiModelProperty(value = "특정게시물 삭제 결과",example = "true",required = true)
    private boolean resultStatus;
}
