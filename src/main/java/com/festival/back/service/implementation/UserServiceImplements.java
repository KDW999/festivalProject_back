package com.festival.back.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.dto.request.user.CheckUserIdRequestDto;
import com.festival.back.dto.request.user.CheckUserNicknameRequestDto;
import com.festival.back.dto.request.user.CheckUserTelNumberRequestDto;
import com.festival.back.dto.request.user.PatchProfileRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.user.CheckUserIdResponseDto;
import com.festival.back.dto.response.user.CheckUserNicknameResponseDto;
import com.festival.back.dto.response.user.CheckUserTelNumberResponseDto;
import com.festival.back.dto.response.user.PatchProfileResponseDto;
import com.festival.back.entity.UserEntity;
import com.festival.back.repository.UserRepository;
import com.festival.back.service.UserService;

@Service
public class UserServiceImplements implements UserService {

    @Autowired UserRepository userRepository;
    
    //? 닉네임 및 프로필 사진 URL 수정 기능     -감재현
    public ResponseDto<PatchProfileResponseDto> patchProfile (String userId, PatchProfileRequestDto dto) {

        PatchProfileResponseDto data = null;

        String nickname = dto.getNickname();
        String profileUrl = dto.getProfileUrl();

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            boolean hasNickname = userRepository.existsByNickname(nickname);
            if (hasNickname) return ResponseDto.setFail(ResponseMessage.EXIST_NICKNAME);
            
            userEntity.setNickname(nickname);
            userEntity.setProfileUrl(profileUrl);
            userRepository.save(userEntity);

            data = new PatchProfileResponseDto(userEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<CheckUserIdResponseDto> checkUserId(CheckUserIdRequestDto dto) {
        CheckUserIdResponseDto data = null;
        String userId=dto.getUserId();
        try {
            boolean hasuserId=userRepository.existsById(userId);
            data = new CheckUserIdResponseDto(hasuserId);

            
        } catch (Exception e) {
            e.printStackTrace();;
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<CheckUserNicknameResponseDto> checkUserNickname(CheckUserNicknameRequestDto dto) {
        CheckUserNicknameResponseDto data= null;
        String nickname=dto.getNickname();
        try {
            boolean hasNickname=userRepository.existsByNickname(nickname);
            data = new CheckUserNicknameResponseDto(hasNickname);
            

        } catch (Exception e) {
            e.printStackTrace();    
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<CheckUserTelNumberResponseDto> checkUserTelNumber(CheckUserTelNumberRequestDto dto) {
        CheckUserTelNumberResponseDto data= null;
        String telNumber=dto.getTelNumber();
        try {
            boolean hasTelNumber=userRepository.existsByTelNumber(telNumber);
            data = new CheckUserTelNumberResponseDto(hasTelNumber);
            

        } catch (Exception e) {
            e.printStackTrace();    
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

}
