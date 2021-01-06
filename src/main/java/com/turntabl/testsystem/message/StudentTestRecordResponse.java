package com.turntabl.testsystem.message;


import java.util.UUID;

public class StudentTestRecordResponse {

    private long student_test_record_id;
    private long test_id;
    private String test_date;
    private UUID student_id;
    private String test_title;

    public StudentTestRecordResponse(long student_test_record_id, long test_id, String test_date, UUID student_id, String test_title) {
        this.student_test_record_id = student_test_record_id;
        this.test_id = test_id;
        this.test_date = test_date;
        this.student_id = student_id;
        this.test_title = test_title;
    }

    public StudentTestRecordResponse() {
    }

    public long getStudent_test_record_id() {
        return student_test_record_id;
    }

    public void setStudent_test_record_id(long student_test_record_id) {
        this.student_test_record_id = student_test_record_id;
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

    public UUID getStudent_id() {
        return student_id;
    }

    public void setStudent_id(UUID student_id) {
        this.student_id = student_id;
    }

    public String getTest_date() {
        return test_date;
    }

    public void setTest_date(String test_date) {
        this.test_date = test_date;
    }
}
