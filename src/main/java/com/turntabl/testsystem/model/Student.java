package com.turntabl.testsystem.model;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "student")
public class Student implements Serializable {
    //student class attributes
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "student_id", updatable = false, nullable = false)
    private UUID student_id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;
    @UpdateTimestamp
    @Column(name = "time_updated")
    private LocalDateTime updatedAt;
    @CreationTimestamp
    @Column(name = "time_created")
    private LocalDateTime createdAt;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<Feedback> feedbacks = new HashSet<>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student",cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<StudentAnswer> studentAnswers = new HashSet<>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<TestResult> testResults = new HashSet<>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<StudentTestRecord> studentTestRecords = new HashSet<>(0);
    public Student() {
    }
    public Student(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }
    public Student(UUID student_id, String first_name, String last_name, String email) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }
    public UUID getStudent_id() {
        return student_id;
    }
    public void setStudent_id(UUID student_id) {
        this.student_id = student_id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }
    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
    public void addFeedback(Feedback feedback){
        this.feedbacks.add(feedback);
    }
    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public void addStudentTestRecord(StudentTestRecord studentTestRecord){
        this.studentTestRecords.add(studentTestRecord);
    }
    public void addStudentAnswer(StudentAnswer studentAnswer){
        this.studentAnswers.add(studentAnswer);
    }
    public void addTestResults(TestResult testResult){
        this.testResults.add(testResult);
    }
}
