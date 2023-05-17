package com.festival.back.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.FreeBoardRecommendEntity;
import com.festival.back.entity.primaryKey.FreeBoardRecommendPk;

@Repository
public interface FreeBoardRecommendRepository extends JpaRepository<FreeBoardRecommendEntity, FreeBoardRecommendPk> {
    
    public List<FreeBoardRecommendEntity>findByFreeBoardNumber(int freeBoardNumber);

    @Transactional
    public void deleteByFreeBoardNumber(int freeBoardNumber);

    public FreeBoardRecommendEntity findByUserIdAndFreeBoardNumber(String userId, int freeBoardNumber);
}
