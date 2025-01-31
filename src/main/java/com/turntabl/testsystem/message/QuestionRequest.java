package com.turntabl.testsystem.message;
import java.util.ArrayList;
import java.util.List;

public class QuestionRequest {
    private long testId;
    private String question;
    private List<String> option = new ArrayList<>(3);
    private String validAnswer;
    private Double mark_allocated;
    private String user_id;
    public QuestionRequest() {
    }

    public QuestionRequest(String question, Double mark_allocated, String validAnswer, List<String> option) {
        this.question = question;
        this.option = option;
        this.validAnswer = validAnswer;
        this.mark_allocated = mark_allocated;
    }

    public QuestionRequest(long testId, String question, List<String> option, String validAnswer, Double mark_allocated, String user_id) {
        this.testId = testId;
        this.question = question;
        this.option = option;
        this.validAnswer = validAnswer;
        this.mark_allocated = mark_allocated;
        this.user_id = user_id;
    }

    public String getValidAnswer() {
        return validAnswer;
    }
    public void setValidAnswer(String validAnswer) {
        this.validAnswer = validAnswer;
    }
    public long getTestId() {
        return testId;
    }
    public void setTestId(long testId) {
        this.testId = testId;
    }
    public Double getMark_allocated() {
        return mark_allocated;
    }
    public void setMark_allocated(Double mark_allocated) {
        this.mark_allocated = mark_allocated;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
