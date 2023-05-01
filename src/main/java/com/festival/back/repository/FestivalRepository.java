package com.festival.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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

      public List<FestivalEntity> findByFestivalArea(String festivalArea);

}