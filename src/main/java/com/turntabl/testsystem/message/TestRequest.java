package com.turntabl.testsystem.message;

public class TestRequest {
    private long course_id;
    private long test_id;
    private String test_title;
    private String questions_type;
    private String test_rules;
    private String test_date;
    private String test_time_start;
    private String test_time_end;
    public TestRequest() {
    }
    public TestRequest(long course_id, long test_id, String test_title, String test_rules, String test_date, String test_time_start, String test_time_end) {
        this.course_id = course_id;
        this.test_id = test_id;
        this.test_title = test_title;
        this.test_rules = test_rules;
        this.test_date = test_date;
        this.test_time_start = test_time_start;
        this.test_time_end = test_time_end;
    }
    public long getCourse_id() {
        return course_id;
    }
    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }
    public long getTest_id() {
        return test_id;
    }
    public void setTest_id(long test_id) {
        this.test_id = test_id;
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
    public String getQuestions_type() {
        return questions_type;
    }
    public void setQuestions_type(String questions_type) {
        this.questions_type = questions_type;
    }
}
