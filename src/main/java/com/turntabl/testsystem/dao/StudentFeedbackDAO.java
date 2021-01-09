package com.turntabl.testsystem.dao;

import com.turntabl.testsystem.message.GeneralAddResponse;
import com.turntabl.testsystem.model.Feedback;
import com.turntabl.testsystem.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentFeedbackDAO{
    @Autowired
    private FeedbackRepository feedbackRepository;
    public StudentFeedbackDAO() {
    }
    public StudentFeedbackDAO(FeedbackRepository feedbackRepository) {
        this.feedbackRepository=feedbackRepository;
    }

    public Feedback get(Long id) {
        Feedback feedback = new Feedback();
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);
        if (optionalFeedback.isPresent()) {
            feedback = optionalFeedback.get();
        }
        return feedback;
    }

    public List<Feedback> addAll(List<Feedback> t) {
       return feedbackRepository.saveAll(t);
    }

    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }

    public Feedback add(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback update(Feedback feedback, String[] params) {
        Feedback feedback_from_db = feedbackRepository.findById(feedback.getFeedbackId()).get();
        feedback_from_db.setFeedback(feedback.getFeedback());
        return feedbackRepository.save(feedback_from_db);
    }

    public GeneralAddResponse delete(Feedback feedback) {
        GeneralAddResponse generalAddResponse = new GeneralAddResponse();
        Optional<Feedback> feedback_found = feedbackRepository.findById(feedback.getFeedbackId());
        if(feedback_found.isPresent()){
            feedbackRepository.delete(feedback);
            generalAddResponse.setMessage("success");
        }else{
            generalAddResponse.setMessage("failed");
        }
        return generalAddResponse;
    }
    public Feedback getByStudent(UUID id){
        Feedback feedback = new Feedback();
        Optional<Feedback> optionalFeedback = feedbackRepository.findByStudentId(id);
        if(optionalFeedback.isPresent()){
            feedback = optionalFeedback.get();
        }
        return feedback;
    }
}
