package com.turntabl.testsystem.message;

public class ResponseMessage {

    private String message;
    private int status_code;

    public ResponseMessage() {
    }

    public ResponseMessage(String message, int status_code) {
        this.message = message;
        this.status_code = status_code;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
