package com.turntabl.testsystem.repository;

import com.turntabl.testsystem.model.Question;
import com.turntabl.testsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select q from Question q where q.testId = ?1")
    List<Question> findQuestionByTestId(long id);
}
