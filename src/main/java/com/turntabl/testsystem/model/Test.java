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
    private Set<TestRule> testRules = new HashSet<>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
    private Set<StudentAnswer> studentAnswers  = new HashSet<>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
    private Set<QuestionsInTest> questionsInTests  = new HashSet<>(0);
    @Column(name = "test_title")
    private String test_title;
    @Column(name = "test_description")
    private String test_description;
    @Column(name = "test_date_and_time")
    private Date test_date_and_time;
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
    public Test(long test_id, Course course, String test_title, String test_description, Date test_date_and_time, Date updatedAt, Date createdAt) {
        this.test_id = test_id;
        this.course = course;
        this.test_title = test_title;
        this.test_description = test_description;
        this.test_date_and_time = test_date_and_time;
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
    public String getTest_title() {
        return test_title;
    }
    public void setTest_title(String test_title) {
        this.test_title = test_title;
    }
    public String getTest_description() {
        return test_description;
    }

    public Set<TestRule> getTestRules() {
        return testRules;
    }

    public void setTestRules(Set<TestRule> testRules) {
        this.testRules = testRules;
    }

    public Set<QuestionsInTest> getQuestionsInTests() {
        return questionsInTests;
    }

    public void setQuestionsInTests(Set<QuestionsInTest> questionsInTests) {
        this.questionsInTests = questionsInTests;
    }

    public void setTest_description(String test_description) {
        this.test_description = test_description;
    }
    public Date getTest_date_and_time() {
        return test_date_and_time;
    }
    public void setTest_date_and_time(Date test_date_and_time) {
        this.test_date_and_time = test_date_and_time;
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
}
