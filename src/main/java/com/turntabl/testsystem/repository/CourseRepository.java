package com.turntabl.testsystem.repository;

import com.turntabl.testsystem.model.Course;
import com.turntabl.testsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select c from Course c where c.course_name = ?1")
    Optional<Course> findByCourseName(String course_name);
}
