package com.festival.back.dto.response.board;

import java.util.List;

import com.festival.back.entity.BoardEntity;
import com.festival.back.entity.CommentEntity;
import com.festival.back.entity.FestivalEntity;
import com.festival.back.entity.RecommendEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "후기 게시글 가져오기 Response Body-data")
public class GetFestivalReviewBoardResponseDto {
    @ApiModelProperty(value = "게시물Entity",required = true)
    private BoardEntity board;
    @ApiModelProperty(value = "댓글 추천 List",required = true)
    private List<RecommendEntity> recommendList ;
    @ApiModelProperty(value = "댓글 List",required = true)
    private List<CommentEntity> commentList;
    @ApiModelProperty(value = "후기 축제 정보",required = true)
    private FestivalEntity festivalBoard;
}
