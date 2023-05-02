package com.festival.back.dto.request.user;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "닉네임 및 프로필 사진 URL 수정 Request Body")
@Data
@NoArgsConstructor
public class PatchProfileRequestDto {

    @ApiModelProperty(value = "변경할 닉네임", example = "홍길동", required = true)
    @NotBlank
    @Length(min=3,max=20)
    private String nickname;

    @ApiModelProperty(value = "변경할 프로필 사진 URL", example = "http://~", required = true)
    @URL
    private String profileUrl;
}
