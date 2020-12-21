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
    @JoinColumn(name = "test_id", nullable = false)
    private Test testId;
    @Column(name = "question")
    private String question;
    @Column(name = "mark_allocated")
    private double mark_allocated;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "question")
    private StudentAnswer studentAnswer;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "question")
    private ValidAnswer validAnswer;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private Set<QuestionsInTest> questionsInTests = new HashSet<>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private Set<Option> options = new HashSet<>(0);
    public Question() {
    }
    public Question(long question_id, Test testId, String question, Double mark_allocated, StudentAnswer studentAnswer, ValidAnswer validAnswer, Set<QuestionsInTest> questionsInTests, Set<Option> options) {
        this.question_id = question_id;
        this.testId = testId;
        this.question = question;
        this.mark_allocated = mark_allocated;
        this.studentAnswer = studentAnswer;
        this.validAnswer = validAnswer;
        this.questionsInTests = questionsInTests;
        this.options = options;
    }
    public StudentAnswer getStudentAnswer() {
        return studentAnswer;
    }
    public void setStudentAnswer(StudentAnswer studentAnswer) {
        this.studentAnswer = studentAnswer;
    }
    public Set<Option> getOptions() {
        return options;
    }
    public void setOptions(Set<Option> options) {
        this.options = options;
    }
    public Test getTestId() {
        return testId;
    }
    public void setTestId(Test testId) {
        this.testId = testId;
    }
    public double getMark_allocated() {
        return mark_allocated;
    }
    public void setMark_allocated(Double mark_allocated) {
        this.mark_allocated = mark_allocated;
    }
    public long getQuestion_id() {
        return question_id;
    }
    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
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
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public void addOption(Option option){this.options.add(option);}
    public void assignTest(Test test){this.testId = test; this.testId.addQuestion(this);}
}
