package com.festival.back.dto.request.board;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("후기 댓글 수정 Request Body")
@Data
@NoArgsConstructor
public class PatchCommentRequestDto {
    
    @ApiModelProperty(value="후기 게시물 번호", example="1", required=true)
    @Min(1)
    private int boardNumber;

    @ApiModelProperty(value="댓글 번호", example="1", required=true)
    @Min(1)
    private int commentNumber;

    @ApiModelProperty(value="후기 게시물 내용", example="Modified Content", required=true)
    @NotBlank
    private String commentContent;
}