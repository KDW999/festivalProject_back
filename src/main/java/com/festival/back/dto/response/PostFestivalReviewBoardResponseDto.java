package com.festival.back.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.festival.back.entity.BoardEntity;
import com.festival.back.entity.CommentEntity;
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

    public PostFestivalReviewBoardResponseDto(BoardEntity board){
        this.board=board;
        this.recommendList=new ArrayList<>();
        this.commentList=new ArrayList<>();
    }
    
}
