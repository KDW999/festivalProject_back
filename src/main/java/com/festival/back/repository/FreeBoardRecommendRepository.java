package com.festival.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.FreeBoardRecommendEntity;
import com.festival.back.entity.primaryKey.FreeBoardRecommendPk;

@Repository
public interface FreeBoardRecommendRepository extends JpaRepository<FreeBoardRecommendEntity, FreeBoardRecommendPk> {
    
}
