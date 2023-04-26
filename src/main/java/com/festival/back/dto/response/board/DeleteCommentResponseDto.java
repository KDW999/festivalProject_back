package com.festival.back.dto.response.board;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="특정 댓글 삭제 Response body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCommentResponseDto {
    @ApiModelProperty(value="특정 댓글 삭제 결과", example="true", required=true)
    private boolean resultStatus;
}
