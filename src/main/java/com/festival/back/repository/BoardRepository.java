package com.festival.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    
    public BoardEntity findByBoardNumber(int boardNumber);
    public List<BoardEntity> findByFestivalNumberOrderByBoardWriteDatetimeDesc(int festivalNumber);
    public List<BoardEntity> findBywriterUserIdOrderByBoardWriteDatetimeDesc(String userId);
    public List<BoardEntity> findByBoardTitleContainsOrBoardContentContainsOrderByBoardWriteDatetimeDesc(String boardTitle,String boardContent);
    public List<BoardEntity> findByOrderByBoardWriteDatetimeDesc();
}
