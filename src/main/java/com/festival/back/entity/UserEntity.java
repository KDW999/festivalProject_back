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
<<<<<<< HEAD

    private String nickname;
=======
>>>>>>> d91b9813e42babb0de2fd99ea077b9ef33e1d600
    private String password;
    private String profileUrl;
    private String nickname;
    private String telNumber;
    private String interestedFestival;
    private boolean adminCheck;
    private boolean reportUser;
    
    public UserEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.nickname = dto.getNickname();
        this.password = dto.getPassword();
        this.profileUrl = dto.getProfileUrl();
        this.telNumber = dto.getTelNumber();
        this.interestedFestival = dto.getInterestedFestival();
    }
}
