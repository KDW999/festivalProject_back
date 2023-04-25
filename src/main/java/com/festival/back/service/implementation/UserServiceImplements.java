package com.festival.back.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.dto.request.user.PatchProfileRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.user.PatchProfileResponseDto;
import com.festival.back.entity.UserEntity;
import com.festival.back.repository.UserRepository;
import com.festival.back.service.UserService;

@Service
public class UserServiceImplements implements UserService {

    @Autowired UserRepository userRepository;
    
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

}
