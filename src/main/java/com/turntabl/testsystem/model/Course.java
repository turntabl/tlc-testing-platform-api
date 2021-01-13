package com.turntabl.testsystem.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="courses")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    private long course_id;
    @Column(name = "course_name")
    private String course_name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", orphanRemoval = true )
    private Set<Test> tests = new HashSet<>(0);
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @UpdateTimestamp
    @Column(name = "time_updated")
    private LocalDateTime updatedAt;
    @CreationTimestamp
    @Column(name = "time_created")
    private LocalDateTime createdAt;
    public Course() {
    }

    public Course(long course_id, String course_name, Set<Test> tests, User user) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.tests = tests;
        this.user = user;
    }

    public long getCourse_id() {
        return course_id;
    }
    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }
    public String getCourse_name() {
        return course_name;
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

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
    public Set<Test> getTests() {
        return tests;
    }
    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
    public void addTest(Test test){
        this.tests.add(test);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void assignUser(User user){
        this.user = user;
        this.user.addCourse(this);
    }

}
