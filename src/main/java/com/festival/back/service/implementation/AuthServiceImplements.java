package com.festival.back.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.festival.back.common.constant.ResponseMessage;
import com.festival.back.dto.request.auth.SignInRequestDto;
import com.festival.back.dto.request.auth.SignUpRequestDto;
import com.festival.back.dto.response.ResponseDto;
import com.festival.back.dto.response.auth.SignInResponseDto;
import com.festival.back.dto.response.auth.SignUpResponseDto;
import com.festival.back.entity.UserEntity;
import com.festival.back.provider.TokenProvider;
import com.festival.back.repository.UserRepository;
import com.festival.back.service.AuthService;

@Service
public class AuthServiceImplements implements AuthService {

    @Autowired UserRepository userRepository;
    @Autowired TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    //? 회원가입 기능      -감재현
    public ResponseDto<SignUpResponseDto> signUp(SignUpRequestDto dto) {
        
        SignUpResponseDto data = null;

        String userId = dto.getUserId();
        String nickname = dto.getNickname();
        String password = dto.getPassword();
        String telNumber = dto.getTelNumber();
        
        try {
            boolean hasUserId = userRepository.existsById(userId);
            if (hasUserId) return ResponseDto.setFail(ResponseMessage.EXIST_ID);

            boolean hasNickname = userRepository.existsByNickname(nickname);
            if (hasNickname) return ResponseDto.setFail(ResponseMessage.EXIST_NICKNAME);

            boolean hasTelNumber = userRepository.existsByTelNumber(telNumber);
            if (hasTelNumber) return ResponseDto.setFail(ResponseMessage.EXIST_TELNUMBER);

            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            data = new SignUpResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
    //? 로그인 기능        -감재현
    public ResponseDto<SignInResponseDto> signIn(SignInRequestDto dto) {

        SignInResponseDto data = null;

        String userId = dto.getUserId();
        String password = dto.getPassword();

        UserEntity userEntity = null;

        try {

            userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.setFail(ResponseMessage.FAIL_SIGN_IN);

            boolean isEqualPassword = passwordEncoder.matches(password, userEntity.getPassword());
            if(!isEqualPassword) return ResponseDto.setFail(ResponseMessage.FAIL_SIGN_IN);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        try {
            String token = tokenProvider.create(userId);
            data = new SignInResponseDto(userEntity, token);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.FAIL_SIGN_IN);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
