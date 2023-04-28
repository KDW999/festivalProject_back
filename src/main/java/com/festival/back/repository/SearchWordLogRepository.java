package com.festival.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.SearchwordLogEntity;

@Repository
public interface SearchWordLogRepository extends JpaRepository<SearchwordLogEntity,Integer>{
    
}
