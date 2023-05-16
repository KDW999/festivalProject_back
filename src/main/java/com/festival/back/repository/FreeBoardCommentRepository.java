package com.festival.back.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festival.back.entity.FreeBoardCommentEntity;

@Repository
public interface FreeBoardCommentRepository extends JpaRepository <FreeBoardCommentEntity, Integer> {
    public List<FreeBoardCommentEntity> findByFreeBoardNumberOrderByWriteDatetimeDesc (int freeBoardNumber);

    @Transactional
    public void deleteByFreeBoardNumber(int freeBoardNumber);
}
