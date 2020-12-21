package com.turntabl.testsystem.message;

public class Answer {
    private long question_id;
    private String answer;

    public Answer(long question_id, String answer) {
        this.question_id = question_id;
        this.answer = answer;
    }

    public Answer() {
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String asnwer) {
        this.answer = asnwer;
    }
}
