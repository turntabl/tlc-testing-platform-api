package com.turntabl.testsystem.message;
import java.util.UUID;

public class TestResultResponse {
    private long test_result_id;
    private String student_name;
    private UUID student_id;
    private String test_title;
    private double test_mark;
    private char test_grade;
    private String comment;

    public TestResultResponse() {
    }

    public TestResultResponse(long test_result_id, String student_name, UUID student_id, String test_title, double test_mark, char test_grade, String comment) {
        this.test_result_id = test_result_id;
        this.student_name = student_name;
        this.student_id = student_id;
        this.test_title = test_title;
        this.test_mark = test_mark;
        this.test_grade = test_grade;
        this.comment = comment;
    }

    public long getTest_result_id() {
        return test_result_id;
    }

    public void setTest_result_id(long test_result_id) {
        this.test_result_id = test_result_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public UUID getStudent_id() {
        return student_id;
    }

    public void setStudent_id(UUID student_id) {
        this.student_id = student_id;
    }

    public String getTest_title() {
        return test_title;
    }

    public void setTest_title(String test_title) {
        this.test_title = test_title;
    }

    public double getTest_mark() {
        return test_mark;
    }

    public void setTest_mark(double test_mark) {
        this.test_mark = test_mark;
    }

    public char getTest_grade() {
        return test_grade;
    }

    public void setTest_grade(char test_grade) {
        this.test_grade = test_grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
