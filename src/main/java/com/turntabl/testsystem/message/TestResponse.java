package com.turntabl.testsystem.message;

public class TestResponse {
    private long test_id;
    private long course_id;
    private String course_name;
    private String question_type;
    private String test_title;
    private String test_rules;
    private String test_date;
    private String test_time_start;
    private String test_time_end;
    public TestResponse() {
    }

    public TestResponse(long test_id, long course_id, String course_name, String question_type, String test_title, String test_rules, String test_date, String test_time_start, String test_time_end) {
        this.test_id = test_id;
        this.course_id = course_id;
        this.course_name = course_name;
        this.question_type = question_type;
        this.test_title = test_title;
        this.test_rules = test_rules;
        this.test_date = test_date;
        this.test_time_start = test_time_start;
        this.test_time_end = test_time_end;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public long getTest_id() {
        return test_id;
    }
    public void setTest_id(long test_id) {
        this.test_id = test_id;
    }
    public long getCourse_id() {
        return course_id;
    }
    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }
    public String getTest_title() {
        return test_title;
    }
    public void setTest_title(String test_title) {
        this.test_title = test_title;
    }
    public String getTest_rules() {
        return test_rules;
    }
    public void setTest_rules(String test_rules) {
        this.test_rules = test_rules;
    }
    public String getTest_date() {
        return test_date;
    }
    public void setTest_date(String test_date) {
        this.test_date = test_date;
    }
    public String getTest_time_start() {
        return test_time_start;
    }
    public void setTest_time_start(String test_time_start) {
        this.test_time_start = test_time_start;
    }
    public String getTest_time_end() {
        return test_time_end;
    }
    public void setTest_time_end(String test_time_end) {
        this.test_time_end = test_time_end;
    }
}
