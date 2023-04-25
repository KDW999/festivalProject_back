package com.festival.back.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.BoardEntity;
import com.festival.back.entity.CommentEntity;
import com.festival.back.entity.FestivalEntity;
import com.festival.back.entity.RecommendEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// ? 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostFestivalReviewBoardResponseDto {
    @ApiModelProperty(value = "게시물Entity",required = true)
    private BoardEntity board;
    @ApiModelProperty(value = "댓글 추천 List",required = true)
    private List<RecommendEntity> recommendList ;
    @ApiModelProperty(value = "댓글 List",required = true)
    private List<CommentEntity> commentList;
    @ApiModelProperty(value = "후기 축제 정보",required = true)
    private FestivalEntity festivalBoard;

    public PostFestivalReviewBoardResponseDto(BoardEntity board,FestivalEntity festivalBoard){
        this.board=board;
        this.festivalBoard=festivalBoard;
        this.recommendList=new ArrayList<>();
        this.commentList=new ArrayList<>();
    }
    
}
