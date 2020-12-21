package com.turntabl.testsystem.message;

import com.turntabl.testsystem.model.Question;
import com.turntabl.testsystem.model.ValidAnswer;

import javax.persistence.*;

public class OptionResponse {
    private long optionId;
    private String option;

    public OptionResponse(long optionId, String option) {
        this.optionId = optionId;
        this.option = option;
    }

    public OptionResponse() {
    }

    public long getOptionId() {
        return optionId;
    }

    public void setOptionId(long optionId) {
        this.optionId = optionId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
