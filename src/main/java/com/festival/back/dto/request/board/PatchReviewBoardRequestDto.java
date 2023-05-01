package com.festival.back.dto.request.board;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "특정 게시물 수정 Request Body")
public class PatchReviewBoardRequestDto {
    @ApiModelProperty(value = "게시물 번호", example = "1", required = true)
    private int boardNumber;

    @ApiModelProperty(value = "축제 번호", example = "1", required = true)
    private int festivalNumber;
    
    @NotBlank
    @ApiModelProperty(value = "게시물 제목", example = "사용할 제목", required = true)
    private String boardTitle;

    @NotBlank
    @ApiModelProperty(value = "게시물 내용", example = "내용작성", required = true)
    private String boardContent;

    @ApiModelProperty(value = "게시물 작성할 이미지", example = "이미지 url", required = false)
    private String boardImgUrl;

}
