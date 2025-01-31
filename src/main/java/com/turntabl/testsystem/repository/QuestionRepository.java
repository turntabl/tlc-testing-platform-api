package com.turntabl.testsystem.repository;

import com.turntabl.testsystem.model.Question;
import com.turntabl.testsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select q from Question q where q.testId.test_id = ?1")
    List<Question> findQuestionByTestId(long id);

    @Query("select q from Question q where q.question = ?1")
    Optional<Question> findByQuestion(String question);

    @Query("select q from Question q where q.question = ?1 and q.testId.test_id = ?2")
    Optional<Question> findByQuestionAndTestID(String question, long id);

}
