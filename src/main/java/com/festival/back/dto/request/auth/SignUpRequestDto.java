package com.festival.back.dto.request.auth;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="회원가입 Request Body")
@Data
@NoArgsConstructor
public class SignUpRequestDto {
    @ApiModelProperty(value="사용자 아이디", example="qwer1234", required=true)
    @NotBlank
    @Length(max=25)
    private String userId;

    @ApiModelProperty(value="사용자 비밀번호", example="qwe123!@", required=true)
    @NotBlank
    @Length(min=8,max=20)
    private String password;

    @ApiModelProperty(value="사용자 프로필 사진URL", example="http://~", required=false)
    @URL
    private String profileUrl;

    @ApiModelProperty(value="사용자 닉네임", example="홍길동", required=true)
    @NotBlank
    @Length(min=3,max=20)
    private String nickname;

    @ApiModelProperty(value="사용자 휴대전화번호", example="010-1234-5678", required=true)
    @NotBlank
    @Length(min=11,max=13)
    private String telNumber;

    @ApiModelProperty(value="사용자의 관심있는 축제", example="OO축제", required=false)
    private List<String> interestedFestival;
}
