package com.festival.back.service;

import com.festival.back.dto.request.user.PatchProfileRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.user.PatchProfileResponseDto;

public interface UserService {
    
    public ResponseDto<PatchProfileResponseDto> patchProfile(String userId, PatchProfileRequestDto dto);
}
