package com.festival.back.dto.request.freeboard;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "추천 기능 Request Body")
@Data
@NoArgsConstructor
public class PatchFreeBoardRequestDto {
    @ApiModelProperty(value = "게시물 제목",example = "군항제 후기",required = true)
    private int boardNumber;

    @NotBlank
    @ApiModelProperty(value = "게시물 제목",example = "군항제 후기",required = true)
    private String boardTitle;

    @NotBlank
    @ApiModelProperty(value = "게시물 제목",example = "군항제 후기",required = true)
    private String boardContent;

    @ApiModelProperty(value = "게시물 제목",example = "군항제 후기",required = true)
    private String boardImgUrl;
}