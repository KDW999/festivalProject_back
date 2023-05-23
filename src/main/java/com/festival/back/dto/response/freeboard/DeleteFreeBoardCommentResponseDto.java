package com.festival.back.dto.response.freeboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "특정 자유 게시물 댓글 삭제 Response Body-data")
public class DeleteFreeBoardCommentResponseDto {
    @ApiModelProperty(value = "특정 자유 게시물 댓글 삭제 결과",example = "true", required = true)
    private boolean resultStatus;
}