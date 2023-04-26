package com.festival.back.dto.request.board;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="댓글 작성 Request Body")
@Data
@NoArgsConstructor
public class PostCommentRequestDto {
    
    @ApiModelProperty(value="게시물 번호", example="1", required=true)
    @Min(1)
    private int boardNumber;

    @ApiModelProperty(value="댓글 내용", example="Comment!", required=true)
    private String commentContent;
}
