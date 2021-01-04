package com.turntabl.testsystem.message;

public class QuestionDetails {
    private long question_id;
    private long testId;
    private String question;
    private Double mark_allocated;
    private String validAnswer;

    public QuestionDetails(long question_id, long testId, String question, Double mark_allocated, String validAnswer) {
        this.question_id = question_id;
        this.testId = testId;
        this.question = question;
        this.mark_allocated = mark_allocated;
        this.validAnswer = validAnswer;
    }

    public QuestionDetails() {
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
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

    public Double getMark_allocated() {
        return mark_allocated;
    }

    public void setMark_allocated(Double mark_allocated) {
        this.mark_allocated = mark_allocated;
    }

    public String getValidAnswer() {
        return validAnswer;
    }

    public void setValidAnswer(String validAnswer) {
        this.validAnswer = validAnswer;
    }
}
