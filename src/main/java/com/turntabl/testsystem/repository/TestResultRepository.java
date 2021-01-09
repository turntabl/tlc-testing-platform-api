package com.turntabl.testsystem.repository;

import com.turntabl.testsystem.model.Test;
import com.turntabl.testsystem.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    @Query("select r from TestResult r where r.student.student_id = ?1")
    Optional<List<TestResult>> findByStudentId(UUID student_id);
    @Query("select r from TestResult r where r.test.test_id = ?1")
    Optional<List<TestResult>> findAllByTestId(long test_id);
    @Query("select r from TestResult r where r.test.test_id = ?1 and r.student.student_id = ?2")
    Optional<TestResult> findByStudentIdAndTestId(long test_id, UUID student_id);
}
