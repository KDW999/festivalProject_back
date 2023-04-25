package com.festival.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.RecommendEntity;
import com.festival.back.entity.primaryKey.RecommendPk;

@Repository
public interface RecommendRepository extends JpaRepository<RecommendEntity, RecommendPk>  {
    
    //? RecommendEntity에 있는 userId와 BoardNumber 찾기
    public RecommendEntity findByUserIdAndBoardNumber(String id, int boardNumber);

    public List<RecommendEntity> findByBoardNumber(int boardNumber);
    public List<RecommendEntity> findByBoardNumber(Integer boardNumber);
}
