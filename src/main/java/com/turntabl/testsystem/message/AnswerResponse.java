package com.turntabl.testsystem.message;

public class AnswerResponse {
    private long student_answer_id;
    private String question;
    private String student_answer;

    public AnswerResponse() {
    }

    public AnswerResponse(long student_answer_id, String question, String student_answer) {
        this.student_answer_id = student_answer_id;
        this.question = question;
        this.student_answer = student_answer;
    }

    public long getStudent_answer_id() {
        return student_answer_id;
    }

    public void setStudent_answer_id(long student_answer_id) {
        this.student_answer_id = student_answer_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public String getStudent_answer() {
        return student_answer;
    }

    public void setStudent_answer(String student_answer) {
        this.student_answer = student_answer;
    }
}
