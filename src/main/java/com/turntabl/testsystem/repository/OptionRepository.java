package com.turntabl.testsystem.repository;

import com.turntabl.testsystem.model.Option;
import com.turntabl.testsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    @Query("select o from Option o where o.question.question_id = ?1 and o.option = ?2")
    Optional<Option> findByQuestionIdAndOption(long question_id, String option);
}
