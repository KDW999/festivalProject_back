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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "특정 축제 특정 후기 게시글 수정 Response")
public class PatchReviewBoardResponseDto {

    @ApiModelProperty(value="게시물 Entity", required=true)
    private BoardEntity board;

    @ApiModelProperty(value="댓글 Entity List", required=true)
    private List<CommentEntity> commentList;

    @ApiModelProperty(value="좋아요 Entity List", required=true)
    private List<RecommendEntity> recommendList;   
}
