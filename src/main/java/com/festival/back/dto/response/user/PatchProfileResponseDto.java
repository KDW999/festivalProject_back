package com.festival.back.dto.response.user;

import com.festival.back.entity.UserEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="닉네임 및 프로필 사진 URL 수정 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchProfileResponseDto {
    
    @ApiModelProperty(value="사용자 아이디", example="qwer1234", required=true)
    private String userId;

    @ApiModelProperty(value="사용자 닉네임", example="홍길동", required=true)
    private String nickname;

    @ApiModelProperty(value="사용자 프로필 사진 URL", example="http://~", required=true)
    private String profileUrl;

    @ApiModelProperty(value="사용자 휴대전화번호", example="010-1234-5678", required=true)
    private String telNumber;

    public PatchProfileResponseDto(UserEntity userEntity) {
        this.userId = userEntity.getUserId();
        this.nickname = userEntity.getNickname();
        this.profileUrl = userEntity.getProfileUrl();
        this.telNumber = userEntity.getTelNumber();
    }
}
