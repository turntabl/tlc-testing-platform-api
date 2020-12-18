package com.turntabl.testsystem.message;

public class GeneralAddResponse {
    private String message;

    public GeneralAddResponse() {
    }

    public GeneralAddResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
