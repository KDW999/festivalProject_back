package com.festival.back.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festival.back.common.constant.ApiPattern;
import com.festival.back.dto.request.user.CheckUserIdRequestDto;
import com.festival.back.dto.request.user.CheckUserNicknameRequestDto;
import com.festival.back.dto.request.user.CheckUserTelNumberRequestDto;
import com.festival.back.dto.request.user.PatchProfileRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.user.CheckUserIdResponseDto;
import com.festival.back.dto.response.user.CheckUserNicknameResponseDto;
import com.festival.back.dto.response.user.CheckUserTelNumberResponseDto;
import com.festival.back.dto.response.user.PatchProfileResponseDto;
import com.festival.back.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(ApiPattern.USER)
public class UserController {
    
    @Autowired UserService userService;
    
    private final String PATCH_PROFILE = "/profile";
    private final String CHECK_USERID = "/check/userid";
    private final String CHECK_NICKNAME = "/check/nickname";
    private final String CHECK_TELNUMBER = "/check/telnumber";
    

    @ApiOperation(value="닉네임 및 프로필 사진 URL 수정하기", notes="Request Header에 Athorization 에 Bearer JWT 를 포함하여 요청하고, 성공시 수정한 닉네임 및 프로필 사진 URL을 반환하고, 실패시 실패 메세지를 반환")
    @PatchMapping(PATCH_PROFILE)
    public ResponseDto<PatchProfileResponseDto> 
    patchProfile(@ApiParam(hidden=true) @AuthenticationPrincipal String userId, 
    @Valid @RequestBody PatchProfileRequestDto requestBody) {
        ResponseDto<PatchProfileResponseDto> response = userService.patchProfile(userId, requestBody);
        return response;
    }
    
    @PostMapping(CHECK_USERID)
    public ResponseDto<CheckUserIdResponseDto> checkUserId(@Valid @RequestBody CheckUserIdRequestDto requestBody){
        ResponseDto<CheckUserIdResponseDto> response=userService.checkUserId(requestBody);
        return response;
    }
    
    @PostMapping(CHECK_NICKNAME)
    public ResponseDto<CheckUserNicknameResponseDto> checkUserNickname(@Valid @RequestBody CheckUserNicknameRequestDto requestBody){
        ResponseDto<CheckUserNicknameResponseDto> response=userService.checkUserNickname(requestBody);
        return response;
    }

    @PostMapping(CHECK_TELNUMBER)
    public ResponseDto<CheckUserTelNumberResponseDto> checkUserTelNumber(@Valid @RequestBody CheckUserTelNumberRequestDto requestBody){
        ResponseDto<CheckUserTelNumberResponseDto> response=userService.checkUserTelNumber(requestBody);
        return response;
    }
}
