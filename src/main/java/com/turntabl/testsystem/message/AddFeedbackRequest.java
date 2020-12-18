package com.turntabl.testsystem.message;
public class AddFeedbackRequest {
    private String message;
    private String student_id;
    public AddFeedbackRequest() {
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getStudent_id() {
        return student_id;
    }
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
}

