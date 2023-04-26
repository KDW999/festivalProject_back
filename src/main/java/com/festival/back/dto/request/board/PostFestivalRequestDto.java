package com.festival.back.dto.request.board;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="축제 작성 Request Body")
@Data
@NoArgsConstructor
public class PostFestivalRequestDto {
    
    @ApiModelProperty(value="축제 이름", example="festival Name", required=true)
    @NotBlank
    private String festivalName;

    @ApiModelProperty(value="축제 타입", example="fetival Type", required=true)
    @NotBlank
    private String festivalType;

    @ApiModelProperty(value="축제 기간 시작", example="2023-04-26", required=true)
    @NotBlank
    private String festivalDurationStart;
    
    @ApiModelProperty(value="축제 기간 끝", example="2023-04-27", required=true)
    @NotBlank
    private String festivalDurationEnd;

    @ApiModelProperty(value="축제 시간", example="15:00", required=true)
    @NotBlank
    private String festivalTime;

    @ApiModelProperty(value="축제 지역", example="부산", required=true)
    @NotBlank
    private String festivalArea;

    @ApiModelProperty(value="축제 비용", example="성인 1500원, 소아 1000원")
    private String festivalCost;

    @ApiModelProperty(value="축제 정보 이미지 URL", example="http://~", required=true)
    private String festivalInformationUrl;

}
