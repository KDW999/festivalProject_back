package com.festival.back.dto.response.festival;

import java.util.List;

import com.festival.back.entity.OneLineReviewEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOneLineReviewResponseDto {
    private List<OneLineReviewEntity> oneLineReviewList;
    
}
