package com.turntabl.testsystem.message;

import java.util.UUID;

public class StudentResultComment {
    private String comment;
    private UUID student_id;
    private long test_id;

    public StudentResultComment() {
    }

    public StudentResultComment(String comment, UUID student_id, long test_id) {
        this.comment = comment;
        this.student_id = student_id;
        this.test_id = test_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UUID getStudent_id() {
        return student_id;
    }

    public void setStudent_id(UUID student_id) {
        this.student_id = student_id;
    }

    public long getTest_id() {
        return test_id;
    }

    public void setTest_id(long test_id) {
        this.test_id = test_id;
    }
}
