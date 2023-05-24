package com.festival.back.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.FestivalEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "회원가입시 선택한 관심 축제 타입 리스트 받아오기 Response Body - data")
public class GetInterestedFestivalListResponseDto {

    @ApiModelProperty(value = "축제 번호",example = "1",required = true)
    private int festivalNumber;

    @ApiModelProperty(value = "축제 이름",example = "OO축제",required = true)
    private String festivalName;

    @ApiModelProperty(value = "축제 타입",example = "꽃축제",required = true)
    private String festivalType;

    @ApiModelProperty(value = "축제 시작 기간",example = "2023-04-22",required = true)
    private String festivalDurationStart;

    @ApiModelProperty(value = "축제 끝나는 기간",example = "2023-05-27",required = true)
    private String festivalDurationEnd;

    @ApiModelProperty(value = "축제 시간",example = "09:00 ~ 20:00",required = true)
    private String festivalTime;

    @ApiModelProperty(value = "축제 지역",example = "부산광역시 OO구",required = true)
    private String festivalArea;
    
    @ApiModelProperty(value = "축제 비용",example = "10000원",required = true)
    private String festivalCost;

    @ApiModelProperty(value = "축제 전체 정보" ,example="울산옹기축제는 옹기의 집산지인 울산 울주군 외고산 옹기마을에서 매년 개최하는 문화관광축제로" ,required = true)
    private String festivalInformation;

    @ApiModelProperty(value = "축제 타이틀 이미지",example = "http url",required = true)
    private String festivalInformationUrl;

    @ApiModelProperty(value = "관광객 평점 평균",example = "7",required = true)
    private double onelineReviewAverage;
    
    public GetInterestedFestivalListResponseDto (FestivalEntity festivalEntity) {
        this.festivalNumber = festivalEntity.getFestivalNumber();
        this.festivalName = festivalEntity.getFestivalName();
        this.festivalType = festivalEntity.getFestivalType();
        this.festivalDurationStart = festivalEntity.getFestivalDurationStart();
        this.festivalDurationEnd = festivalEntity.getFestivalDurationEnd();
        this.festivalTime = festivalEntity.getFestivalTime();
        this.festivalArea = festivalEntity.getFestivalArea();
        this.festivalCost = festivalEntity.getFestivalCost();
        this.festivalInformation=festivalEntity.getFestivalInformation();
        this.festivalInformationUrl=festivalEntity.getFestivalInformationUrl();
        this.onelineReviewAverage=festivalEntity.getOnelineReviewAverage();
    }

    public static List<GetInterestedFestivalListResponseDto> copyList(List<FestivalEntity> festivaleEntityList) {
        List<GetInterestedFestivalListResponseDto> list = new ArrayList<>();

        for (FestivalEntity festivalEntity : festivaleEntityList) {
            GetInterestedFestivalListResponseDto dto = new GetInterestedFestivalListResponseDto(festivalEntity);
            list.add(dto);
        }
        return list;
    }
}
