package com.festival.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.FestivalEntity;

@Repository
public interface FestivalRepository extends JpaRepository<FestivalEntity,Integer>  {

      public FestivalEntity FindByFestivalNumber(int festivalNumber);
}
