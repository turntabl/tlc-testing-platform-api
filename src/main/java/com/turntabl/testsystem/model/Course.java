package com.turntabl.testsystem.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private Set<Test> tests = new HashSet<>(0);
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_updated")
    private Date updatedAt;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_created")
    private Date createdAt;
    public Course() {
    }
    public Course(long course_id, String course_name, Set<Test> tests) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.tests = tests;
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
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
    public Set<Test> getTests() {
        return tests;
    }
    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
}
