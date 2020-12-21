package com.turntabl.testsystem.model;

public enum QuestionType {
    MULTIPLE_CHOICE("MC"),CODE_SNIPPET("CS"),ESSAY("E");

    private String code;

     QuestionType(String code) {
        this.code = code;
    }

    public String getCode(){
         return code;
    }
}
