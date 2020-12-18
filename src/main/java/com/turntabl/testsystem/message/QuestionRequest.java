package com.turntabl.testsystem.message;

import com.turntabl.testsystem.model.Option;

import java.util.ArrayList;
import java.util.List;

public class QuestionRequest {
    private long testId;
    private String question;
    private List<String> option = new ArrayList<>(3);
    private String validAnswer;

    public QuestionRequest() {
    }

    public String getValidAnswer() {
        return validAnswer;
    }

    public void setValidAnswer(String validAnswer) {
        this.validAnswer = validAnswer;
    }

    public QuestionRequest(long testId, String question, List<String> option, String validAnswer) {
        this.testId = testId;
        this.question = question;
        this.option = option;
        this.validAnswer = validAnswer;
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }
}
