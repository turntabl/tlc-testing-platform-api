package com.turntabl.testsystem.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="student_test_records")
public class StudentTestRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_test_record_id")
    private long student_test_record_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
    @UpdateTimestamp
    @Column(name = "time_updated")
    private LocalDateTime updatedAt;
    @CreationTimestamp
    @Column(name = "time_created")
    private LocalDateTime createdAt;

    public StudentTestRecord() {
    }

    public StudentTestRecord(long student_test_record_id, Student student, Test test) {
        this.student_test_record_id = student_test_record_id;
        this.student = student;
        this.test = test;
    }

    public long getStudent_test_record_id() {
        return student_test_record_id;
    }

    public void setStudent_test_record_id(long student_test_record_id) {
        this.student_test_record_id = student_test_record_id;
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

    public void assignStudent(Student student){
        this.student = student;
        this.student.addStudentTestRecord(this);
    }

    public void assignTest(Test test){
        this.test = test;
        this.test.addStudentTestRecord(this);
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
