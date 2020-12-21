package com.turntabl.testsystem.message;

public class Answer {
    private long question_id;
    private long option_id;
    private String answer;

    public Answer(long question_id, long option_id, String answer) {
        this.question_id = question_id;
        this.option_id = option_id;
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

    public long getOption_id() {
        return option_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public void setOption_id(long option_id) {
        this.option_id = option_id;
    }
}
