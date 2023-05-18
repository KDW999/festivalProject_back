package com.festival.back.dto.request.board;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
// ? 김종빈 작성 
@Data
@NoArgsConstructor
@ApiModel(value = "후기 게시글 작성. Request Body")
public class PostReviewBoardRequestDto {
    @ApiModelProperty(value = "작성할 축제 구분 번호",example = "1",required = true)
    private int festivalNumber;

    @NotBlank
    @ApiModelProperty(value = "게시물 제목",example = "군항제 후기",required = true)
    private String boardTitle;
    
    @NotBlank
    @ApiModelProperty(value = "후기 내용",example = "여기 좋아요",required = true)
    private String boardContent;
    
    @ApiModelProperty(value = "첨부할 이미지",example = "업로드할 img url",required = true)
    private String boardImgUrl;
        
}
