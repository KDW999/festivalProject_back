package com.festival.back.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.festival.back.common.constant.ApiPattern;
import com.festival.back.dto.request.user.PatchProfileRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.user.PatchProfileResponseDto;
import com.festival.back.service.UserService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(ApiPattern.USER)
public class UserController {
    
    @Autowired UserService userService;
    
    private final String PATCH_PROFILE = "/profile";

    @PatchMapping(PATCH_PROFILE)
    public ResponseDto<PatchProfileResponseDto> 
    patchProfile(@ApiParam(hidden=true) @AuthenticationPrincipal String userId, 
    @Valid @RequestBody PatchProfileRequestDto requestBody) {
        ResponseDto<PatchProfileResponseDto> response = userService.patchProfile(userId, requestBody);
        return response;
    }

}
