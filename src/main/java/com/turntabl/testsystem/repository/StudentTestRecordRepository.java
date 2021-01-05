package com.turntabl.testsystem.repository;

import com.turntabl.testsystem.model.StudentTestRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentTestRecordRepository extends JpaRepository<StudentTestRecord, Long> {
    @Query("select sr from StudentTestRecord sr where sr.student.student_id = ?1")
    List<Optional<StudentTestRecord>> findAllTestsTakenByStudentId(UUID student_id);
    @Query("select sr from StudentTestRecord sr where sr.test.test_id = ?1")
    List<Optional<StudentTestRecord>> findAllTestsTakenByTestId(long test_id);
}
