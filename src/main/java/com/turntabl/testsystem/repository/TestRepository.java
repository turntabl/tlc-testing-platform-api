package com.turntabl.testsystem.repository;

import com.turntabl.testsystem.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    @Query("select t from Test t where t.test_title = ?1")
    Optional<Test> findByTestTitle(String test_title);
}
