package com.turntabl.testsystem.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private long question_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_type_id", nullable = false)
    private QuestionType questionType;
    @Column(name = "question")
    private String question;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "question")
    private StudentAnswer studentAnswer;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "question")
    private ValidAnswer validAnswer;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private Set<QuestionsInTest> questionsInTests = new HashSet<>(0);
    public Question() {
    }
    public Question(long question_id, QuestionType questionType, String question) {
        this.question_id = question_id;
        this.questionType = questionType;
        this.question = question;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public ValidAnswer getValidAnswer() {
        return validAnswer;
    }

    public void setValidAnswer(ValidAnswer validAnswer) {
        this.validAnswer = validAnswer;
    }

    public Set<QuestionsInTest> getQuestionsInTests() {
        return questionsInTests;
    }

    public void setQuestionsInTests(Set<QuestionsInTest> questionsInTests) {
        this.questionsInTests = questionsInTests;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
