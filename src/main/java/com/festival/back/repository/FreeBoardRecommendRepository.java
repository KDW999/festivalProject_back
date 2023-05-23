package com.festival.back.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.FreeBoardRecommendEntity;
import com.festival.back.entity.primaryKey.FreeBoardRecommendPk;

@Repository
public interface FreeBoardRecommendRepository extends JpaRepository<FreeBoardRecommendEntity, FreeBoardRecommendPk> {
    
    public List<FreeBoardRecommendEntity>findByBoardNumber(int boardNumber);

    @Transactional
    public void deleteByBoardNumber(int boardNumber);

    public FreeBoardRecommendEntity findByUserIdAndBoardNumber(String userId, int boardNumber);
}
