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

      public FestivalEntity findByFestivalName(String festivalName);

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
                  "AND  festival_area = ? " +
                  "ORDER BY festival_duration_start", nativeQuery = true)
      public List<FestivalEntity> getFestivalMonth1(String monthDate1, String monthDate2, String monthDate3,String monthDate4, String area);

      // ? 같이 써서 하고 싶을 떄
      @Query(value = "SELECT * " +
                  "FROM Festival " +
                  "WHERE (festival_duration_start < ? AND festival_duration_end >= ? " +
                  "OR festival_duration_start >= ? AND festival_duration_start < ?) " +
                  "AND  festival_area LIKE ? " +
                  "ORDER BY festival_duration_start", nativeQuery = true)
      // area = "%" + data + "%";
      public List<FestivalEntity> getFestivalMonth2(String monthDate1, String monthDate2, String monthDate3,String monthDate4, String area);

      @Query(value=" SELECT DISTINCT festival_type FROM festival ORDER BY festival_type DESC ", nativeQuery=true)
      public List<String> getFestivalTypeList();
    
       @Query(value = " SELECT *  FROM Festival WHERE ? <= festival_duration_start AND ?> festival_duration_start ORDER BY festival_duration_start ASC LIMIT 1 " ,nativeQuery =  true)
      public FestivalEntity getTop1OneLineReview(String date1,String date2);

      //? 이런 방법도 있다고 했지만 이렇게 쓸 경우 원하는 결과가 나오지 않았음. 그래서 front에서 filter로 만들었음.
      public List<FestivalEntity> findByFestivalDurationStartGreaterThanEqualOrderByFestivalDurationStartAsc(String festivalDurationStart);
      public List<FestivalEntity> findBy();
}