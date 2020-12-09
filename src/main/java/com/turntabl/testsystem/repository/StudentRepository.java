package com.turntabl.testsystem.repository;
import com.turntabl.testsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    @Query("select s from Student s where s.email = ?1")
    Optional<Student> findByEmail(String email);
}
