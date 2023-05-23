package com.festival.back.service;

import com.festival.back.dto.request.user.CheckUserIdRequestDto;
import com.festival.back.dto.request.user.CheckUserNicknameRequestDto;
import com.festival.back.dto.request.user.CheckUserTelNumberRequestDto;
import com.festival.back.dto.request.user.PatchProfileRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.user.CheckUserIdResponseDto;
import com.festival.back.dto.response.user.CheckUserNicknameResponseDto;
import com.festival.back.dto.response.user.CheckUserTelNumberResponseDto;
import com.festival.back.dto.response.user.GetUserResponseDto;
import com.festival.back.dto.response.user.PatchProfileResponseDto;

public interface UserService {
    
    public ResponseDto<GetUserResponseDto> getUser(String userId);

    public ResponseDto<PatchProfileResponseDto> patchProfile(String userId, PatchProfileRequestDto dto);
    
    public ResponseDto<CheckUserIdResponseDto> checkUserId(CheckUserIdRequestDto dto);
    public ResponseDto<CheckUserNicknameResponseDto> checkUserNickname(CheckUserNicknameRequestDto dto);
    public ResponseDto<CheckUserTelNumberResponseDto> checkUserTelNumber(CheckUserTelNumberRequestDto dto);
}
