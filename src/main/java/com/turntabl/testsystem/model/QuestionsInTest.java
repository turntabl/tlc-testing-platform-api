package com.turntabl.testsystem.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="questions_in_tests")
public class QuestionsInTest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "questions_in_test_id")
    private long questions_in_test_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    public QuestionsInTest() {
    }

    public QuestionsInTest(long questions_in_test_id, Test test, Question question) {
        this.questions_in_test_id = questions_in_test_id;
        this.test = test;
        this.question = question;
    }

    public long getQuestions_in_test_id() {
        return questions_in_test_id;
    }

    public void setQuestions_in_test_id(long questions_in_test_id) {
        this.questions_in_test_id = questions_in_test_id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
