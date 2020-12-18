package com.turntabl.testsystem.message;

import com.turntabl.testsystem.model.Option;
import java.util.HashSet;
import java.util.Set;

public class QuestionResponse {
    private long questionId;
    private String question;
    private Set<Option> options = new HashSet<>(0);
    public QuestionResponse() {
    }
    public QuestionResponse(long questionId, String question, Set<Option> options) {
        this.questionId = questionId;
        this.question = question;
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
    public void setQuestion(String question) {
        this.question = question;
    }
    public Set<Option> getOptions() {
        return options;
    }
    public void setOptions(Set<Option> options) {
        this.options = options;
    }
}
