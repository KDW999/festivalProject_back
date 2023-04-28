package com.festival.back.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.FestivalEntity;

@Repository
public interface FestivalRepository extends JpaRepository<FestivalEntity,Integer>  {
      public FestivalEntity findByFestivalNumber(int festivalNumber);
      public FestivalEntity findByFestivalName(String festivalName);

      public List<FestivalEntity> findByFestivalTypeIn(List<String> interestedFestivalTypeList);

      public List<FestivalEntity>
       findByFestivalNameContainsOrFestivalTypeContainsOrFestivalInformationContainsOrFestivalAreaOrderByFestivalDurationStartDesc
(String festivalName,String festivalType,String festivalInformation,String festivalArea);


// WHERE festival_duration_start < '2023-02-01' AND festival_duration_end >= '2023-02-01'
// OR festival_duration_start >= '2023-02-01' AND festival_duration_start < '2023-03-01';
      @Query(
      value="SELECT * " +
      "FROM Festival " +
      "WHERE festival_duration_start < ? AND festival_duration_end >= ? " +
      "OR festival_duration_start >= ? AND festival_duration_start < ? " +
      "ORDER BY festival_duration_start", nativeQuery=true)
      public List<FestivalEntity> getFestivalMonth(String monthDate1, String monthDate2, String monthDate3, String monthDate4);

}