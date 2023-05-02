package com.festival.back.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festival.back.common.constant.ApiPattern;
import com.festival.back.dto.request.auth.SignInRequestDto;
import com.festival.back.dto.request.auth.SignUpRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.auth.SignInResponseDto;
import com.festival.back.dto.response.auth.SignUpResponseDto;
import com.festival.back.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "인증 모듈")
@RestController
@RequestMapping(ApiPattern.AUTH)
public class AuthController {

    @Autowired AuthService authService;

    private final String SIGN_UP = "/sign-up";
    private final String SIGN_IN = "/sign-in";

    //? 회원가입 기능       -감재현
    @ApiOperation(value="회원가입", notes="아이디, 비밀번호, 사용자 프로필 사진URL, 닉네임, 전화번호, 사용자의 관심있는 축제를 입력하여 회원을 등록하고, 성공 시에는 회원가입 성공 여부에 true가 반환됨")
    @PostMapping(SIGN_UP)
    public ResponseDto<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestBody) {
        ResponseDto<SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    //? 로그인 기능         -감재현
    @ApiOperation(value="로그인", notes="아이디와 비밀번호를 입력하면 일치할 경우, 회원 정보와 토큰 그리고 토큰 만료기간을 반환하고, 실패한다면 해당 메세지를 반환")
    @PostMapping(SIGN_IN)
    public ResponseDto<SignInResponseDto> signIn(@Valid @RequestBody SignInRequestDto requestBody) {
        ResponseDto<SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
}