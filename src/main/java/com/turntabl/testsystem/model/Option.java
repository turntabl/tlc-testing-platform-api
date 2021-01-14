package com.turntabl.testsystem.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="options")
public class Option implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "option_id")
    private long optionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    @Column(name = "option")
    private String option;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "option", cascade = CascadeType.ALL, orphanRemoval = true )
    private ValidAnswer validAnswer;
    @UpdateTimestamp
    @Column(name = "time_updated")
    private LocalDateTime updatedAt;
    @CreationTimestamp
    @Column(name = "time_created")
    private LocalDateTime createdAt;
    public Option() {
    }

    public Option(long optionId, Question question, String option, ValidAnswer validAnswer) {
        this.optionId = optionId;
        this.question = question;
        this.option = option;
        this.validAnswer = validAnswer;
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

    public long getOptionId() {
        return optionId;
    }
    public void setOptionId(long optionId) {
        this.optionId = optionId;
    }
    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
    public String getOption() {
        return option;
    }
    public void setOption(String option) {
        this.option = option;
    }

    public ValidAnswer getValidAnswer() {
        return validAnswer;
    }

    public void setValidAnswer(ValidAnswer validAnswer) {
        this.validAnswer = validAnswer;
        validAnswer.setOption(this);
    }

    public void assignQuestion(Question question){this.question = question; this.question.addOption(this);}
}
