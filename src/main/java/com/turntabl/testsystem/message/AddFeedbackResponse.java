package com.turntabl.testsystem.message;

public class AddFeedbackResponse {
    private String message;
    private String student_name;
    private long id;
    public AddFeedbackResponse() {
    }
    public AddFeedbackResponse(String message, String student_name, Long id) {
        this.message = message;
        this.student_name = student_name;
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getStudent_name() {
        return student_name;
    }
    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
