package com.festival.back.dto.request.auth;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="로그인 Request Body")
@Data
@NoArgsConstructor
public class SignInRequestDto {
    @ApiModelProperty(value="사용자 아이디", example="qwer1234", required=true)
    @NotBlank
    @Length(max=25)
    private String userId;
    @ApiModelProperty(value="사용자 비밀번호", example="qwe123!@", required=true)
    @NotBlank
    @Length(min=8,max=20)
    private String password;
}
