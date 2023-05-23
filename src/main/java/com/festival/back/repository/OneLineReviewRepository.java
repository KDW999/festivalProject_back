package com.festival.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.OneLineReviewEntity;
import com.festival.back.entity.primaryKey.OneLineReviewPk;

@Repository
public interface OneLineReviewRepository extends JpaRepository<OneLineReviewEntity, OneLineReviewPk>{

    public OneLineReviewEntity findByUserIdAndFestivalNumber(String userId, int festivalNumber);
    
    public List<OneLineReviewEntity> findByFestivalNumberOrderByWriteDatetimeDesc(int festivalNumber);

    public OneLineReviewEntity findByUserId(String userId);

    public boolean existsByUserId(String userId);

    public boolean existsByFestivalNumber(int festivalNumber);



    

    // public OneLineReviewEntity deleteByFestivalNumberAndUserId(int fetivalNumber, String userId);
    
}
