package com.festival.back.dto.request.freeboard;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatchFreeBoardCommentRequestDto {
    @Min(1)
    private int freeBoardNumber;

    @Min(1)
    private int freeBoardCommentNumber;

    @NotBlank
    private String freeBoardCommentContent;
}
