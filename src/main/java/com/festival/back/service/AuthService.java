package com.festival.back.service;

import com.festival.back.dto.request.auth.SignInRequestDto;
import com.festival.back.dto.request.auth.SignUpRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.auth.SignInResponseDto;
import com.festival.back.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    
    public ResponseDto<SignUpResponseDto> signUp(SignUpRequestDto dto);
    public ResponseDto<SignInResponseDto> signIn(SignInRequestDto dto);
}
