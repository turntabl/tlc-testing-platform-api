package com.turntabl.testsystem.message;

import com.turntabl.testsystem.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AddQuestionsResponse {
    private AtomicInteger atomicInteger;
    private List<QuestionDetails> questions = new ArrayList<>();

    public AddQuestionsResponse(AtomicInteger atomicInteger, List<QuestionDetails> questions) {
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

    public List<QuestionDetails> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDetails> questions) {
        this.questions = questions;
    }
}
