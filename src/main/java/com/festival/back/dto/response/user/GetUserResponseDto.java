package com.festival.back.dto.response.user;

import com.festival.back.entity.UserEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "유저 정보 불러오기 Response Body - data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponseDto {
    
    @ApiModelProperty(value = "유저 아이디", example = "kdw12345", required = true)
    private String userId;

    @ApiModelProperty(value = "유저 닉네임", example = "Lion King", required = true)
    private String nickname;

    @ApiModelProperty(value = "유저 프로필 URL", example = "http:~~", required = true)
    private String profileUrl;

    @ApiModelProperty(value = "유저 전화번호", example = "010-1234-5423", required = true)
    private String telNumber;

    public GetUserResponseDto(UserEntity userEntity){
        this.userId = userEntity.getUserId();
        this.nickname = userEntity.getNickname();
        this.profileUrl = userEntity.getProfileUrl();
        this.telNumber = userEntity.getTelNumber();
    }
}
