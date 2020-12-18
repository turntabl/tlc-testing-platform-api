package com.turntabl.testsystem.message;

public class OptionRequest {
    private String option;
    public OptionRequest() {
    }
    public OptionRequest(String option) {
        this.option = option;
    }
    public String getOption() {
        return option;
    }
    public void setOption(String option) {
        this.option = option;
    }
}
