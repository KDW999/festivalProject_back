package com.festival.back.dto.response.board;

import java.util.List;

import com.festival.back.entity.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetSearchFestivalReviewBoardListResponseDto {
    private List<BoardEntity> boardList;
    
}
