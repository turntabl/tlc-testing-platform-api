package com.turntabl.testsystem.repository;

import com.turntabl.testsystem.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
