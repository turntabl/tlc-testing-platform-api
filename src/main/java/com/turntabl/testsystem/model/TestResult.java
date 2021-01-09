package com.turntabl.testsystem.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_results")
public class TestResult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_result_id")
    private long test_result_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
    @Column(name = "test_mark")
    private double test_mark;
    @Column(name = "test_grade")
    private char test_grade;
    @Column(name = "comment")
    private String comment;
    @UpdateTimestamp
    @Column(name = "time_updated")
    private LocalDateTime updatedAt;
    @CreationTimestamp
    @Column(name = "time_created")
    private LocalDateTime createdAt;


    public TestResult() {
    }

    public TestResult(long test_result_id, Student student, Test test, double test_mark, char test_grade, String comment, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.test_result_id = test_result_id;
        this.student = student;
        this.test = test;
        this.test_mark = test_mark;
        this.test_grade = test_grade;
        this.comment = comment;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public long getTest_result_id() {
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

    public double getTest_mark() {
        return test_mark;
    }

    public void setTest_mark(Double test_mark) {
        this.test_mark = test_mark;
    }

    public char getTest_grade() {
        return test_grade;
    }

    public void setTest_grade(Character test_grade) {
        this.test_grade = test_grade;
    }

    public void assignTest(Test test){this.test = test; this.test.addTestResult(this);}

    public void assignStudent(Student student){this.student = student; this.student.addTestResults(this);}

    public void setTest_result_id(long test_result_id) {
        this.test_result_id = test_result_id;
    }

    public void setTest_mark(double test_mark) {
        this.test_mark = test_mark;
    }

    public void setTest_grade(char test_grade) {
        this.test_grade = test_grade;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
