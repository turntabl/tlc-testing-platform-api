package com.turntabl.testsystem.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="feedbacks")
public class Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "feedback_id")
    private long feedbackId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @Column(name = "feedback")
    private String feedback;
    @UpdateTimestamp
    @Column(name = "time_updated")
    private LocalDateTime updatedAt;
    @CreationTimestamp
    @Column(name = "time_created")
    private LocalDateTime createdAt;
    //constructor
    public Feedback() {
    }
    public Feedback(long feedbackId, Student student, String feedback) {
        this.feedbackId = feedbackId;
        this.student = student;
        this.feedback = feedback;
    }
    //getters and setters
    public long getFeedbackId() {
        return feedbackId;
    }
    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public String getFeedback() {
        return feedback;
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

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public void assignToStudent(Student student){
        this.student = student;
        this.student.addFeedback(this);
    }
}
