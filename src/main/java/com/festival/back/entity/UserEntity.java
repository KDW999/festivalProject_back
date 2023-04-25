package com.festival.back.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.festival.back.dto.request.auth.SignUpRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "User")
public class UserEntity {
    @Id
    private String userId;
    private String nickname;
    private String password;
    private String profileUrl;
    private String telNumber;
    private boolean adminCheck;
    private boolean reportUser;
    private String interestedFestival;
    
    public UserEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.nickname = dto.getNickname();
        this.password = dto.getPassword();
        this.profileUrl = dto.getProfileUrl();
        this.telNumber = dto.getTelNumber();
        this.interestedFestival = dto.getInterestedFestival();
    }
}
