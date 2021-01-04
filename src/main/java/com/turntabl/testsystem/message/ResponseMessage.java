package com.turntabl.testsystem.message;
public class ResponseMessage<T> {
    private String message;
    private int status_code;
    private T data;
    public ResponseMessage() {
    }

    public ResponseMessage(String message, int status_code, T t) {
        this.message = message;
        this.status_code = status_code;
        this.data = t;
    }

    public int getStatus_code() {
        return status_code;
    }
    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public T getT() {
        return data;
    }

    public void setT(T t) {
        this.data = t;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
