package com.festival.back.dto.request.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "유저 전화번호 체크 Request Body")
@Data
@NoArgsConstructor
public class CheckUserTelNumberRequestDto {
    private String telNumber;
    
}
