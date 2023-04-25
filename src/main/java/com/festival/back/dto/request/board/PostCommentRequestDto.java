package com.festival.back.dto.request.board;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCommentRequestDto {
    
    private int boardNumber;
    private String commentContent;
}
