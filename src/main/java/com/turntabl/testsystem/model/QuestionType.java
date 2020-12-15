package com.turntabl.testsystem.model;

import org.aspectj.weaver.patterns.TypePatternQuestions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "question_types")
public class QuestionType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_type_id")
    private long question_type_id;
    @Column(name = "question_type")
    private String question_type;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "questionType")
    private Set<Question> questions = new HashSet<>(0);

    public QuestionType() {
    }

    public QuestionType(long question_type_id, String question_type, Set<Question> questions) {
        this.question_type_id = question_type_id;
        this.question_type = question_type;
        this.questions = questions;
    }

    public long getQuestion_type_id() {
        return question_type_id;
    }

    public void setQuestion_type_id(long question_type_id) {
        this.question_type_id = question_type_id;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
