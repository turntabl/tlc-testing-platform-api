package com.turntabl.testsystem.message;

import java.util.HashSet;
import java.util.Set;

public class QuestionResponse {
    private long questionId;
    private String question;
    private Double mark_allocated;
    private Set<OptionResponse> options = new HashSet<>(0);
    public QuestionResponse() {
    }
    public QuestionResponse(long questionId, String question, Double mark_allocated, Set<OptionResponse> options) {
        this.questionId = questionId;
        this.question = question;
        this.mark_allocated = mark_allocated;
        this.options = options;
    }
    public long getQuestionId() {
        return questionId;
    }
    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
    public String getQuestion() {
        return question;
    }
    public Double getMark_allocated() {
        return mark_allocated;
    }
    public void setMark_allocated(Double mark_allocated) {
        this.mark_allocated = mark_allocated;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public Set<OptionResponse> getOptions() {
        return options;
    }
    public void setOptions(Set<OptionResponse> options) {
        this.options = options;
    }
}
