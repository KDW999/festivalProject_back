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
import com.festival.back.dto.response.user.GetUserResponseDto;
import com.festival.back.dto.response.user.PatchProfileResponseDto;
import com.festival.back.entity.UserEntity;
import com.festival.back.repository.UserRepository;
import com.festival.back.service.UserService;

@Service
public class UserServiceImplements implements UserService {

    @Autowired UserRepository userRepository;

    //? 유저 조회
    public ResponseDto<GetUserResponseDto> getUser(String userId) {

        GetUserResponseDto data = null;

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);

            if(userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            data = new GetUserResponseDto(userEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
    //? 닉네임 및 프로필 사진 URL 수정 기능     -감재현
    public ResponseDto<PatchProfileResponseDto> patchProfile (String userId, PatchProfileRequestDto dto) {

        PatchProfileResponseDto data = null;

        String nickname = dto.getNickname();
        String profileUrl = dto.getProfileUrl();

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_USER);

            String existUser = userEntity.getNickname();
            System.out.println(nickname);
            System.out.println(existUser);
            boolean hasNickname = userRepository.existsByNickname(nickname);

            if (hasNickname) {
                //? DB에 저장되어있는 데이터의 닉네임과 Dto로 빋은 닉네임 일치
                if(nickname.equals(existUser)){
                    userEntity.setProfileUrl(profileUrl);
                } else {
                    return ResponseDto.setFail(ResponseMessage.EXIST_NICKNAME);
                }
            }

            if(!nickname.equals(existUser)) {
                userEntity.setNickname(nickname);
            }

            if(profileUrl != null){
                userEntity.setProfileUrl(profileUrl);
            }
            
            userRepository.save(userEntity);
            data = new PatchProfileResponseDto(userEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 중복 아이디 검증
    public ResponseDto<CheckUserIdResponseDto> checkUserId(CheckUserIdRequestDto dto) {
        CheckUserIdResponseDto data = null;
        String userId=dto.getUserId();
        try {
            boolean hasuserId=userRepository.existsById(userId);
            data = new CheckUserIdResponseDto(hasuserId);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //? 중복 닉네임 검증
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

    //? 중복 전화번호 검증
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
