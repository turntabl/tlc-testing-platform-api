package com.turntabl.testsystem.model;

public enum Role {
    SUPER_ADMIN(1),ADMIN(2);

    private int code;

    Role(int code) {
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
