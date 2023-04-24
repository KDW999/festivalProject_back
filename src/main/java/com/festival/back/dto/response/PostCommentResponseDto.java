package com.festival.back.dto.response;

import java.util.List;

import com.festival.back.entity.BoardEntity;
import com.festival.back.entity.CommentEntity;
import com.festival.back.entity.RecommendEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentResponseDto {
    
    @ApiModelProperty(value="게시물 Entity", required=true)
    private BoardEntity board;

    @ApiModelProperty(value="댓글 Entity List", required=true)
    private List<CommentEntity> commentList;

    @ApiModelProperty(value="좋아요 Entity List")
    private List<RecommendEntity> recommendList;
}
