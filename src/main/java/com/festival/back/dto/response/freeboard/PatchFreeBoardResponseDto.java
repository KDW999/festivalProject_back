package com.festival.back.dto.response.freeboard;

import java.util.List;

import com.festival.back.entity.FreeBoardCommentEntity;
import com.festival.back.entity.FreeBoardEntity;
import com.festival.back.entity.FreeBoardRecommendEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchFreeBoardResponseDto {
    
    private FreeBoardEntity boardEntity;

    private List<FreeBoardCommentEntity> commentList;

    private List<FreeBoardRecommendEntity> recommendList;
}
