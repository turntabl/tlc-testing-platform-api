package com.turntabl.testsystem.message;

import java.util.List;

public class StudentAnswerRequest {
    private String student_id;
    private long test_id;
    private List<Answer> answers;

    public StudentAnswerRequest() {
    }

    public StudentAnswerRequest(String student_id, long test_id, List<Answer> answers) {
        this.student_id = student_id;
        this.test_id = test_id;
        this.answers = answers;
    }

    public long getTest_id() {
        return test_id;
    }

    public void setTest_id(long test_id) {
        this.test_id = test_id;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
}
