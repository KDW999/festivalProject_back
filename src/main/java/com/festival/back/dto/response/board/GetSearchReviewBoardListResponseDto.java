package com.festival.back.dto.response.board;

import java.util.List;

import com.festival.back.entity.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSearchReviewBoardListResponseDto {
    private List<BoardEntity> boardList;
}
