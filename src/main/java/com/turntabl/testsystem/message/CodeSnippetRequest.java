package com.turntabl.testsystem.message;

public class CodeSnippetRequest {
    private String question;
    private String code_snippet;
    private long test_id;
    private String user_id;
    private Double mark_allocated;

    public CodeSnippetRequest(String question, String code_snippet, long test_id, String user_id, Double mark_allocated) {
        this.question = question;
        this.code_snippet = code_snippet;
        this.test_id = test_id;
        this.user_id = user_id;
        this.mark_allocated = mark_allocated;
    }

    public CodeSnippetRequest() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCode_snippet() {
        return code_snippet;
    }

    public void setCode_snippet(String code_snippet) {
        this.code_snippet = code_snippet;
    }

    public long getTest_id() {
        return test_id;
    }

    public void setTest_id(long test_id) {
        this.test_id = test_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Double getMark_allocated() {
        return mark_allocated;
    }

    public void setMark_allocated(Double mark_allocated) {
        this.mark_allocated = mark_allocated;
    }
}
