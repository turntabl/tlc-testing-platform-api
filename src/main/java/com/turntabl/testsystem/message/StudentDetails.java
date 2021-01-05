package com.turntabl.testsystem.message;

import java.util.UUID;

public class StudentDetails {
    private String message;
    private UUID student_id;
    private String first_name;
    private String last_name;
    private String email;

    public StudentDetails() {
    }

    public StudentDetails(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getStudent_id() {
        return student_id;
    }
    public void setStudent_id(UUID student_id) {
        this.student_id = student_id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
