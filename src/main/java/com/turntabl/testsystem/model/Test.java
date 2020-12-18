package com.turntabl.testsystem.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tests")
public class Test implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_id")
    private long test_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
    private Set<StudentAnswer> studentAnswers  = new HashSet<>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
    private Set<QuestionsInTest> questionsInTests  = new HashSet<>(0);
    @Column(name = "test_title")
    private String test_title;
    @Column(name = "test_rules")
    private String test_rules;
    @Column(name = "test_date")
    private String test_date;
    @Column(name = "test_time_start")
    private String test_time_start;
    @Column(name = "test_time_end")
    private String test_time_end;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_updated")
    private Date updatedAt;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_created")
    private Date createdAt;
    public Test() {
    }

    public Test(long test_id, Course course, Set<StudentAnswer> studentAnswers, Set<QuestionsInTest> questionsInTests, String test_title, String test_rules, String test_date, String test_time_start, String test_time_end, Date updatedAt, Date createdAt) {
        this.test_id = test_id;
        this.course = course;
        this.studentAnswers = studentAnswers;
        this.questionsInTests = questionsInTests;
        this.test_title = test_title;
        this.test_rules = test_rules;
        this.test_date = test_date;
        this.test_time_start = test_time_start;
        this.test_time_end = test_time_end;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public long getTest_id() {
        return test_id;
    }

    public void setTest_id(long test_id) {
        this.test_id = test_id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<StudentAnswer> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(Set<StudentAnswer> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }

    public Set<QuestionsInTest> getQuestionsInTests() {
        return questionsInTests;
    }

    public void setQuestionsInTests(Set<QuestionsInTest> questionsInTests) {
        this.questionsInTests = questionsInTests;
    }

    public String getTest_title() {
        return test_title;
    }

    public void setTest_title(String test_title) {
        this.test_title = test_title;
    }

    public String getTest_rules() {
        return test_rules;
    }

    public void setTest_rules(String test_rules) {
        this.test_rules = test_rules;
    }

    public String getTest_date() {
        return test_date;
    }

    public void setTest_date(String test_date) {
        this.test_date = test_date;
    }

    public String getTest_time_start() {
        return test_time_start;
    }

    public void setTest_time_start(String test_time_start) {
        this.test_time_start = test_time_start;
    }

    public String getTest_time_end() {
        return test_time_end;
    }

    public void setTest_time_end(String test_time_end) {
        this.test_time_end = test_time_end;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void assignCourse(Course course){this.course = course; this.course.addTest(this);}
}
