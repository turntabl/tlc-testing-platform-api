package com.turntabl.testsystem.repository;
import com.turntabl.testsystem.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query("select f from Feedback f where f.student.student_id = ?1")
    Optional<Feedback> findByStudentId(UUID id);
}
