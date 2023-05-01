package com.festival.back.dto.request.festival;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "한 줄 평 작성 Request Body")
@Data
@NoArgsConstructor
public class PostOneLineReviewRequestDto {
    
    @ApiModelProperty(value = "한 줄 평 평점", example = "3", required = true)
    @Min(0)
    @Max(10)
    private int average;

    @ApiModelProperty(value = "한 줄 평 내용", example = "풍경이 예뻐요", required = true)
    @NotBlank
    private String oneLineReviewContent;

    @ApiModelProperty(value = "축제 번호", example = "1", required = true)
    @Min(1)
    private int festivalNumber;
}
