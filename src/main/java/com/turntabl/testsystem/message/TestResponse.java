package com.turntabl.testsystem.message;

import java.util.UUID;

public class TestResponse {
    private String message;
    private long test_id;
    private long course_id;
    private String course_name;
    private String questions_type;
    private String test_title;
    private String test_rule;
    private String test_date;
    private String test_time_start;
    private String test_time_end;
    private UUID user_id;
    public TestResponse() {
    }



    public TestResponse(String message, long test_id, long course_id, String course_name, String questions_type, String test_title, String test_rule, String test_date, String test_time_start, String test_time_end, UUID user_id) {
        this.message = message;
        this.test_id = test_id;
        this.course_id = course_id;
        this.course_name = course_name;
        this.questions_type = questions_type;
        this.test_title = test_title;
        this.test_rule = test_rule;
        this.test_date = test_date;
        this.test_time_start = test_time_start;
        this.test_time_end = test_time_end;
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getQuestions_type() {
        return questions_type;
    }

    public void setQuestions_type(String questions_type) {
        this.questions_type = questions_type;
    }

    public String getTest_title() {
        return test_title;
    }

    public void setTest_title(String test_title) {
        this.test_title = test_title;
    }

    public String getTest_rule() {
        return test_rule;
    }

    public void setTest_rule(String test_rule) {
        this.test_rule = test_rule;
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

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }
}
