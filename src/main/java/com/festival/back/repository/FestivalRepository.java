package com.festival.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.FestivalEntity;
import com.festival.back.entity.InterestedFestivalEntity;

@Repository
public interface FestivalRepository extends JpaRepository<FestivalEntity,Integer>  {
      public FestivalEntity findByFestivalNumber(int festivalNumber);

<<<<<<< HEAD
      public List<FestivalEntity> findByFestivalType(List<InterestedFestivalEntity> interestedFestivalTypeList);

=======
>>>>>>> b17ae4fda64a20f62a7d25a81cfa961d2148c842
}
