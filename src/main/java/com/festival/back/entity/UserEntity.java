package com.festival.back.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String password;
    private String profileUrl;
    private String nickname;
    private String telNumber;
    private boolean adminCheck;
    private boolean reportUser;
    private String interestedFestival;
    
}
