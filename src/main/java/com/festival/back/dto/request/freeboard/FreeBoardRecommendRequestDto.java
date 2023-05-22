package com.festival.back.dto.request.freeboard;

import javax.validation.constraints.Min;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FreeBoardRecommendRequestDto {
    @Min(1)
    private int boardNumber;
}
