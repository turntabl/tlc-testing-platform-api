package com.turntabl.testsystem.model;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name = "valid_answer")
    private String valid_answer;

    public ValidAnswer() {
    }

    public ValidAnswer(long valid_answer_id, Question question, String valid_answer) {
        this.valid_answer_id = valid_answer_id;
        this.question = question;
        this.valid_answer = valid_answer;
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

    public String getValid_answer() {
        return valid_answer;
    }

    public void setValid_answer(String valid_answer) {
        this.valid_answer = valid_answer;
    }
}
