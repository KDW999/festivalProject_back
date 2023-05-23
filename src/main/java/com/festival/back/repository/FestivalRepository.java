package com.festival.back.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.FestivalEntity;

@Repository
public interface FestivalRepository extends JpaRepository<FestivalEntity, Integer> {

      public FestivalEntity findByFestivalNumber(int festivalNumber);
      public List<FestivalEntity> findByOrderByFestivalDurationStartAsc();

      @Query(value = "select * from festival where festival_name OR festival_area like %?% " , nativeQuery = true)
      public List<FestivalEntity> searchName(String festivalName);

      public List<FestivalEntity> findByFestivalTypeIn(List<String> interestedFestivalTypeList);

      public List<FestivalEntity> findByFestivalNameContainsOrFestivalTypeContainsOrFestivalInformationContainsOrFestivalAreaOrderByFestivalDurationStartDesc(
                  String festivalName, String festivalType, String festivalInformation, String festivalArea);

      // ? 지역별 개최날짜 빠른 순
      public List<FestivalEntity> findByFestivalAreaContainingOrderByFestivalDurationStart(String festivalArea);
      
      @Modifying
      @Transactional
      @Query(value = "UPDATE festival " +
                  "SET oneline_review_average = (SELECT AVG(average) FROM onelinereview WHERE festival_number = ?)" +
                  "WHERE festival_number = ?", nativeQuery = true)
      public int setAverger(int festivalNumber, int festivalNumber2);

      @Query(value = "SELECT * " +
                  "FROM Festival " +
                  "WHERE festival_duration_start < ? AND festival_duration_end >= ? " +
                  "OR festival_duration_start >= ? AND festival_duration_start < ? " +
                  "ORDER BY festival_duration_start", nativeQuery = true)
      public List<FestivalEntity> getFestivalMonth(String monthDate1, String monthDate2, String monthDate3,String monthDate4);

      @Query(value = "SELECT * " +
                  "FROM Festival " +
                  "WHERE (festival_duration_start < ? AND festival_duration_end >= ? " +
                  "OR festival_duration_start >= ? AND festival_duration_start < ?) " +
                  "AND  festival_area = ? " +
                  "ORDER BY festival_duration_start", nativeQuery = true)
      public List<FestivalEntity> getFestivalMonth1(String monthDate1, String monthDate2, String monthDate3,String monthDate4, String area);

      @Query(value = "SELECT * " +
                  "FROM Festival " +
                  "WHERE (festival_duration_start < ? AND festival_duration_end >= ? " +
                  "OR festival_duration_start >= ? AND festival_duration_start < ?) " +
                  "AND  festival_area LIKE ? " +
                  "ORDER BY festival_duration_start", nativeQuery = true)
      public List<FestivalEntity> getFestivalMonth2(String monthDate1, String monthDate2, String monthDate3,String monthDate4, String area);

      @Query(value=" SELECT DISTINCT festival_type FROM festival ORDER BY festival_type DESC ", nativeQuery=true)
      public List<String> getFestivalTypeList();

      @Query(value = " SELECT *  FROM Festival WHERE ? <= festival_duration_start AND ?> festival_duration_start ORDER BY festival_duration_start ASC LIMIT 1 " ,nativeQuery =  true)
      public FestivalEntity getTop1OneLineReview(String date1,String date2);

      public List<FestivalEntity> findBy();
}