package com.turntabl.testsystem.dao;

import com.turntabl.testsystem.model.Feedback;

import java.util.List;

public interface FeedbackDAO {
    Feedback get(Long id);
    List<Feedback> addAll(List<Feedback> t);
    List<Feedback> getAll();
    Feedback add(Feedback feedback);
    Feedback update(Feedback feedback, String[] params);
    boolean delete(Feedback feedback);
}
