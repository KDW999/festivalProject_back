package com.festival.back.dto.request.board;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostBoardDto {
    
    @ApiModelProperty(value="게시물 제목", example="Board Title", required=true)
    @NotBlank
    private String boardTitle;

    @ApiModelProperty(value="게시물 내용", example="Board Content", required=true)
    @NotBlank
    private String boardContent;

    @ApiModelProperty(value="게시물 이미지 URL", example="http://~", required=true)
    private String boardImgUrl;
}
