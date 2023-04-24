package com.festival.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    

    public boolean existsById(String userId);
    public boolean existsByNickname(String nickname);
    public boolean existsByTelNumber(String TelNumber);

    public UserEntity findByUserId(String userId);
}
