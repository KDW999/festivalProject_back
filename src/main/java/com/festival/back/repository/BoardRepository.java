package com.festival.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.BoardEntity;
import com.festival.back.entity.FestivalEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer>  {
  
}
