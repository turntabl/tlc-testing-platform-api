package com.turntabl.testsystem.repository;

import com.turntabl.testsystem.model.ValidAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidAnswerRepository extends JpaRepository<ValidAnswer, Long> {
    @Query("select v from ValidAnswer v where v.question.question_id = ?1")
    Optional<ValidAnswer> findByQuestionId(long question_id);
}
