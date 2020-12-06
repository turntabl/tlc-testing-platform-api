package com.turntabl.testsystem.message;

public class ResponseMessage {

    private String message;
    private int status_code;
    private int total_number;

    public ResponseMessage() {
    }

    public ResponseMessage(String message, int status_code, int total_number) {
        this.message = message;
        this.status_code = status_code;
        this.total_number = total_number;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
