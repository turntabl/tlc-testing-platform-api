package com.turntabl.testsystem.message;

import com.turntabl.testsystem.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AddQuestionsResponse {
    private AtomicInteger atomicInteger;
    private List<Question> questions = new ArrayList<>();

    public AddQuestionsResponse(AtomicInteger atomicInteger, List<Question> questions) {
        this.atomicInteger = atomicInteger;
        this.questions = questions;
    }

    public AddQuestionsResponse() {
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public void setAtomicInteger(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
