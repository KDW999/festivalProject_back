package com.festival.back.dto.response.festival;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.OneLineReviewEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "특정 축제 전체 한줄 평가 리스트 출력.")
public class GetOneLineReviewResponseDto {
    @ApiModelProperty(value = "축제 평점",example = "3",required = true)
    private int average;
    @ApiModelProperty(value = "한줄 평점 내용",example = "빙어가 많아요",required = true)
    private String oneLineReviewContent;
    @ApiModelProperty(value = "작성자 프로파일 사진",example = "http:// url",required = true)
    private String userProfileUrl;
    @ApiModelProperty(value = "작성자 닉네임",example = "jonh",required = true)
    private String userNickname;
    @ApiModelProperty(value = "작성 날짜",example = "jonh",required = true)
    private String writeDatetime;


    public GetOneLineReviewResponseDto(OneLineReviewEntity oneLineReviewEntity){
        this.average=oneLineReviewEntity.getAverage();
        this.oneLineReviewContent=oneLineReviewEntity.getOneLineReviewContent();
        this.userProfileUrl=oneLineReviewEntity.getUserProfileUrl();
        this.userNickname=oneLineReviewEntity.getUserNickname();
        this.writeDatetime=oneLineReviewEntity.getWriteDatetime();
    } 
   
    public static List<GetOneLineReviewResponseDto> copyList(List<OneLineReviewEntity> oneLineReviewList){
        List<GetOneLineReviewResponseDto> list=new ArrayList<>();

        for(OneLineReviewEntity oneLineReviewEntity : oneLineReviewList) {
            GetOneLineReviewResponseDto dto = new GetOneLineReviewResponseDto(oneLineReviewEntity);
            list.add(dto);

        }
        return list;
    }




    
}
