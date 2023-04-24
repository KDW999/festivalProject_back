package com.festival.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.RecommendEntity;
import com.festival.back.entity.primaryKey.RecommendPk;

@Repository
<<<<<<< HEAD
public interface RecommendRepository extends JpaRepository<RecommendEntity, RecommendPk>  {
    
=======
public interface RecommendRepository extends JpaRepository<RecommendEntity, RecommendPk>{
    
    //? RecommendEntity에 있는 userId와 BoardNumber 찾기
    public RecommendEntity findByUserIdAndBoardNumber(String id, int boardNumber);

>>>>>>> d91b9813e42babb0de2fd99ea077b9ef33e1d600
    public List<RecommendEntity> findByBoardNumber(int boardNumber);
}
