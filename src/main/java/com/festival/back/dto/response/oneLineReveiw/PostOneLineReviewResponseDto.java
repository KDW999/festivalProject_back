package com.festival.back.dto.response.oneLineReveiw;

import java.util.List;

import com.festival.back.entity.FestivalEntity;
import com.festival.back.entity.OneLineReviewEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "한 줄 평 작성 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOneLineReviewResponseDto {
    //? 한 줄 평은 축제 정보 게시물에 달린 댓글과 같다.

    @ApiModelProperty(value = "축제 정보 Entity", required = true)
    private FestivalEntity festival;

    @ApiModelProperty(value = "한 줄 평 Entity List", required = true)
    private List<OneLineReviewEntity> oneLineReviewList;

    // @ApiModelProperty(value = "한 줄 평 평점", example = "3", required = true)
    // private int average;

    // @ApiModelProperty(value = "한 줄 평 내용", example = "풍경이 예뻐요.", required = true)
    // private String oneLineReviewContent;

    // @ApiModelProperty(value = "유저 아이디", example = "kdw123", required = true)
    // private String userId;

    // @ApiModelProperty(value = "유저 프로필 이미지", example = "http://~", required = false)
    // private String userProfileUrl;

    // @ApiModelProperty(value = "유저 닉네임", example = "muyaho", required = true)
    // private String userNickname;

    // @ApiModelProperty(value = "축제 번호", example = "1", required = true)
    // private int festivalNumber;

    // public PostOneLineReviewResponseDto(OneLineReviewEntity review){
    //     this.average = review.getAverage();
    //     this.oneLineReviewContent = review.getOneLineReviewContent();
    //     this.userId = review.getUserId();
    //     this.userProfileUrl = review.getUserProfileUrl();
    //     this.userNickname = review.getUserNickname();
    //     this.festivalNumber = review.getFestivalNumber();
    // }


}
