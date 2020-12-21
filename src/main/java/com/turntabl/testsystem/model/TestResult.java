package com.turntabl.testsystem.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test_results")
public class TestResult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_result_id")
    private Long test_result_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
    @Column(name = "test_mark")
    private Double test_mark;
    @Column(name = "test_grade")
    private Character test_grade;

    public TestResult() {
    }

    public TestResult(Long test_result_id, Student student, Test test, Double test_mark, Character test_grade) {
        this.test_result_id = test_result_id;
        this.student = student;
        this.test = test;
        this.test_mark = test_mark;
        this.test_grade = test_grade;
    }

    public Long getTest_result_id() {
        return test_result_id;
    }

    public void setTest_result_id(Long test_result_id) {
        this.test_result_id = test_result_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Double getTest_mark() {
        return test_mark;
    }

    public void setTest_mark(Double test_mark) {
        this.test_mark = test_mark;
    }

    public Character getTest_grade() {
        return test_grade;
    }

    public void setTest_grade(Character test_grade) {
        this.test_grade = test_grade;
    }
}
