package com.turntabl.testsystem.message;

public class FindByEmailRequestMessage {

    private String email;

    public FindByEmailRequestMessage(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
