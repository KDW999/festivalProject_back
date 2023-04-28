package com.festival.back.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.FestivalEntity;
import com.festival.back.entity.OneLineReviewEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="축제 작성 Response body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostFestivalResponseDto {
    
    @ApiModelProperty(value="축제 이름", example="festival Name", required=true)
    private String festivalName;

    @ApiModelProperty(value="축제 타입", example="fetival Type", required=true)
    private String festivalType;

    @ApiModelProperty(value="축제 기간 시작", example="2023-04-26", required=true)
    private String festivalDurationStart;
    
    @ApiModelProperty(value="축제 기간 끝", example="2023-04-27", required=true)
    private String festivalDurationEnd;

    @ApiModelProperty(value="축제 시간", example="15:00", required=true)
    private String festivalTime;

    @ApiModelProperty(value="축제 지역", example="부산", required=true)
    private String festivalArea;

    @ApiModelProperty(value="축제 비용", example="성인 1500원, 소아 1000원")
    private String festivalCost;

    @ApiModelProperty(value="축제 정보 이미지 URL", example="http://~", required=true)
    private String festivalInformationUrl;

    @ApiModelProperty(value="축제 한줄 평가 내용 리스트", example="5.0", required=true)
    private List<OneLineReviewEntity> oneLineReviewContentList;

    public PostFestivalResponseDto(FestivalEntity festivalEntity) {
        this.festivalName = festivalEntity.getFestivalName();
        this.festivalType = festivalEntity.getFestivalType();
        this.festivalDurationStart = festivalEntity.getFestivalDurationStart();
        this.festivalDurationEnd = festivalEntity.getFestivalDurationEnd();
        this.festivalTime = festivalEntity.getFestivalTime();
        this.festivalArea = festivalEntity.getFestivalArea();
        this.festivalCost = festivalEntity.getFestivalCost();
        this.festivalInformationUrl = festivalEntity.getFestivalInformationUrl();
        this.oneLineReviewContentList = new ArrayList<>();
    }
}