package com.festival.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.InterestedFestivalEntity;

@Repository
public interface InterestedFestivalRepository extends JpaRepository<InterestedFestivalEntity, Integer> {
    
    public List<InterestedFestivalEntity> findByUserId(String userId);
}
