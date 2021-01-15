package com.turntabl.testsystem.message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AddEssayQuestionResponse {
    private AtomicInteger atomicInteger;
    private List<EssayQuestionDetails> essayQuestionDetails = new ArrayList<>();

    public AddEssayQuestionResponse() {
    }

    public AddEssayQuestionResponse(AtomicInteger atomicInteger, List<EssayQuestionDetails> essayQuestionDetails) {
        this.atomicInteger = atomicInteger;
        this.essayQuestionDetails = essayQuestionDetails;
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public void setAtomicInteger(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }

    public List<EssayQuestionDetails> getEssayQuestionDetails() {
        return essayQuestionDetails;
    }

    public void setEssayQuestionDetails(List<EssayQuestionDetails> essayQuestionDetails) {
        this.essayQuestionDetails = essayQuestionDetails;
    }
}
