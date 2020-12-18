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
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "question_type_id", nullable = false)
//    private QuestionType questionType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test testId;
    @Column(name = "question")
    private String question;
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
    public Question(long question_id, QuestionType questionType, String question, StudentAnswer studentAnswer, ValidAnswer validAnswer, Set<QuestionsInTest> questionsInTests, Set<Option> options) {
        this.question_id = question_id;
        //this.questionType = questionType;
        this.question = question;
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
    public long getQuestion_id() {
        return question_id;
    }
    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }
//   // public QuestionType getQuestionType() {
//        return questionType;
//    }
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
//
//    //public void setQuestionType(QuestionType questionType) {
//        this.questionType = questionType;
//    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public void addOption(Option option){this.options.add(option);}
    public void assignTest(Test test){this.testId = test; this.testId.addQuestion(this);}
}
