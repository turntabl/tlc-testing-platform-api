package com.turntabl.testsystem.repository;

import com.turntabl.testsystem.model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {
    @Query("select s from StudentAnswer s where s.question.question_id = ?1 and s.test.test_id = ?2")
    Optional<List<StudentAnswer>> findAllByQuestionIdAndTestId(long question_id, long test_id);
    @Query("select s from StudentAnswer s where s.student.student_id = ?1 and s.test.test_id = ?2")
    Optional<List<StudentAnswer>> findAllByStudentIdAndTestId(UUID id, long test_id);
}
