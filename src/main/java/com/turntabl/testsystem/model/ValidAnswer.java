package com.turntabl.testsystem.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "valid_answers")
public class ValidAnswer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "valid_answer_id")
    private long valid_answer_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false)
    private Option option;
    @UpdateTimestamp
    @Column(name = "time_updated")
    private LocalDateTime updatedAt;
    @CreationTimestamp
    @Column(name = "time_created")
    private LocalDateTime createdAt;

    public ValidAnswer() {
    }

    public ValidAnswer(long valid_answer_id, Question question, Option option) {
        this.valid_answer_id = valid_answer_id;
        this.question = question;
        this.option = option;
    }

    public long getValid_answer_id() {
        return valid_answer_id;
    }

    public void setValid_answer_id(long valid_answer_id) {
        this.valid_answer_id = valid_answer_id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
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
