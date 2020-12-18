package com.turntabl.testsystem.repository;

import com.turntabl.testsystem.model.ValidAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidAnswerRepository extends JpaRepository<ValidAnswer, Long> {
}
