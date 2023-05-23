package com.festival.back.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.FreeBoardCommentEntity;

@Repository
public interface FreeBoardCommentRepository extends JpaRepository <FreeBoardCommentEntity, Integer> {
    
    public List<FreeBoardCommentEntity> findByBoardNumberOrderByWriteDatetimeDesc (int boardNumber);
    public FreeBoardCommentEntity findByCommentNumber(int commentNumber);

    @Transactional
    public void deleteByBoardNumber(int boardNumber);
}