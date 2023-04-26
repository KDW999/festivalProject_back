package com.festival.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.CommentEntity;
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    public List<CommentEntity> findByBoardNumberOrderByWriteDatetimeDesc(int boardNumber);
    public CommentEntity findByCommentNumber(int commentNumber);
}
