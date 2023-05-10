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

      public FestivalEntity findByFestivalName(String festivalName);

      public List<FestivalEntity> findByFestivalTypeIn(List<String> interestedFestivalTypeList);

      public List<FestivalEntity> findByFestivalNameContainsOrFestivalTypeContainsOrFestivalInformationContainsOrFestivalAreaOrderByFestivalDurationStartDesc(
                  String festivalName, String festivalType, String festivalInformation, String festivalArea);

      // ? 지역별 개최날짜 빠른 순
      public List<FestivalEntity> findByFestivalAreaOrderByFestivalDurationStart(String festivalArea);
      
      @Modifying
      @Transactional
      @Query(value = "UPDATE festival " +
                  "SET oneline_review_average = (SELECT AVG(average) FROM onelinereview WHERE festival_number = ?) " +
                  "WHERE festival_number = ?",nativeQuery = true)
      public int setAverger(int festivalNumber,int festivalNumber2);

      // WHERE festival_duration_start < '2023-02-01' AND festival_duration_end >=
      // '2023-02-01'
      // OR festival_duration_start >= '2023-02-01' AND festival_duration_start <
      // '2023-03-01';

      // ^ 월별, 지역별 같이 보여주고 싶으면 Service의 하나의 기능에서 조건(월별만, 지역만, 월별 지역 둘 다)을 걸어서 원하는 쿼리문을
      // 실행

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
                  "AND  festival_area = ?" +
                  "ORDER BY festival_duration_start", nativeQuery = true)
      public List<FestivalEntity> getFestivalMonth1(String monthDate1, String monthDate2, String monthDate3,String monthDate4, String area);

      // ? 같이 써서 하고 싶을 떄
      @Query(value = "SELECT * " +
                  "FROM Festival " +
                  "WHERE (festival_duration_start < ? AND festival_duration_end >= ? " +
                  "OR festival_duration_start >= ? AND festival_duration_start < ?) " +
                  "AND  festival_area LIKE ?" +
                  "ORDER BY festival_duration_start", nativeQuery = true)
      // area = "%" + data + "%";
      public List<FestivalEntity> getFestivalMonth2(String monthDate1, String monthDate2, String monthDate3,String monthDate4, String area);
}