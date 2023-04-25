package com.festival.back.dto.response.board;

import java.util.List;

import com.festival.back.entity.BoardEntity;
import com.festival.back.entity.CommentEntity;
import com.festival.back.entity.RecommendEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="후기 댓글 수정 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchCommentResponseDto {
    
    @ApiModelProperty(value="게시물 Entity", required=true)
    private CommentEntity comment;

    @ApiModelProperty(value="추천 EntityList", required=true)
    private List<RecommendEntity> recommendList;

    @ApiModelProperty(value="댓글 EntityList", required=true)
    private List<CommentEntity> commentList;
}
