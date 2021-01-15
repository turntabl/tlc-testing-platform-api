package com.turntabl.testsystem.message;

public class EssayQuestionRequest {
    private String question;
    private long test_id;
    private String user_id;
    private Double mark_allocated;

    public EssayQuestionRequest(String question, long test_id, String user_id, Double mark_allocated) {
        this.question = question;
        this.test_id = test_id;
        this.user_id = user_id;
        this.mark_allocated = mark_allocated;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public EssayQuestionRequest() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String questions) {
        this.question = questions;
    }

    public long getTest_id() {
        return test_id;
    }

    public void setTest_id(long test_id) {
        this.test_id = test_id;
    }

    public Double getMark_allocated() {
        return mark_allocated;
    }

    public void setMark_allocated(Double mark_allocated) {
        this.mark_allocated = mark_allocated;
    }
}
