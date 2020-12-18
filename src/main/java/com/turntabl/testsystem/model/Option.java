package com.turntabl.testsystem.model;

import javax.persistence.*;
import java.io.Serializable;

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
    public Option() {
    }
    public Option(long optionId, Question question, String option) {
        this.optionId = optionId;
        this.question = question;
        this.option = option;
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
    public void assignQuestion(Question question){this.question = question; this.question.addOption(this);}
}
