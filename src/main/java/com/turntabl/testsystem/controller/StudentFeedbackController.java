package com.turntabl.testsystem.controller;
import com.turntabl.testsystem.dao.StudentDAO;
import com.turntabl.testsystem.dao.StudentFeedbackDAO;
import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.message.AddFeedbackRequest;
import com.turntabl.testsystem.message.AddFeedbackResponse;
import com.turntabl.testsystem.message.GeneralAddResponse;
import com.turntabl.testsystem.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentFeedbackController {
    private StringToUserIdConverter stringToUserIdConverter = new StringToUserIdConverter();
    @Autowired
    private final StudentDAO studentDAO;
    @Autowired
    private StudentFeedbackDAO studentFeedbackDAO;
    public StudentFeedbackController(StudentDAO studentDAO, StudentFeedbackDAO studentFeedbackDAO) {
        this.studentDAO = studentDAO;
        this.studentFeedbackDAO = studentFeedbackDAO;
    }
    @GetMapping("/feedbacks")
    public ResponseEntity<List<AddFeedbackResponse>> getAllFeedbacks() {
        try {
            List<AddFeedbackResponse> addFeedbackResponses = new ArrayList<>();
            List<Feedback> feedbacks = studentFeedbackDAO.getAll();
            if(!feedbacks.isEmpty()){
                addFeedbackResponses = feedbacks.stream()
                        .map(feedback -> {
                            AddFeedbackResponse addFeedbackResponse = new AddFeedbackResponse();
                            addFeedbackResponse.setMessage(feedback.getFeedback());
                            addFeedbackResponse.setId(feedback.getFeedbackId());
                            addFeedbackResponse.setStudent_name(feedback.getStudent().getFirst_name() + " " + feedback.getStudent().getLast_name());
                            return addFeedbackResponse;
                        }).collect(Collectors.toList());
                return new ResponseEntity<>(addFeedbackResponses, HttpStatus.OK);
            }
            return new ResponseEntity<>(addFeedbackResponses , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/feedback/{id}")
    public ResponseEntity<AddFeedbackResponse> getFeedbackById(@PathVariable long id) {
        Feedback feedback = studentFeedbackDAO.get(id);
        AddFeedbackResponse addFeedbackResponse = new AddFeedbackResponse();
        addFeedbackResponse.setMessage(feedback.getFeedback());
        addFeedbackResponse.setId(feedback.getFeedbackId());
        addFeedbackResponse.setStudent_name(feedback.getStudent().getFirst_name() + " " + feedback.getStudent().getLast_name());
        return new ResponseEntity<>(addFeedbackResponse, HttpStatus.OK);
    }
    @GetMapping("/delete/feedback/{id}")
    public ResponseEntity<GeneralAddResponse> deleteFeedbackById(@PathVariable long id) {
        try{
            GeneralAddResponse generalAddResponse = studentFeedbackDAO.delete(studentFeedbackDAO.get(id));
            return new ResponseEntity<>(generalAddResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new GeneralAddResponse(e.getMessage()), HttpStatus.OK);
        }
    }
    @GetMapping("/feedback/find/{id}")
    public ResponseEntity<AddFeedbackResponse> getFeedbackByStudentId(@PathVariable String id) {
        Feedback feedback = studentFeedbackDAO.getByStudent(stringToUserIdConverter.convert(id));
        AddFeedbackResponse addFeedbackResponse = new AddFeedbackResponse();
        addFeedbackResponse.setMessage(feedback.getFeedback());
        addFeedbackResponse.setId(feedback.getFeedbackId());
        addFeedbackResponse.setStudent_name(feedback.getStudent().getFirst_name() + " " + feedback.getStudent().getLast_name());
        return new ResponseEntity<>(addFeedbackResponse, HttpStatus.OK);
    }
    @PostMapping("/feedback")
    public ResponseEntity<AddFeedbackResponse> addFeedback(@RequestBody AddFeedbackRequest addFeedbackRequest){
        try {
            AddFeedbackResponse addFeedbackResponse = new AddFeedbackResponse();
            Feedback feedback = new Feedback();
            feedback.setFeedback(addFeedbackRequest.getMessage());
            feedback.assignToStudent(studentDAO.get(stringToUserIdConverter.convert(addFeedbackRequest.getStudent_id())));
            Feedback saved_feedback = studentFeedbackDAO.add(feedback);
            addFeedbackResponse.setMessage(saved_feedback.getFeedback());
            addFeedbackResponse.setId(saved_feedback.getFeedbackId());
            addFeedbackResponse.setStudent_name(saved_feedback.getStudent().getFirst_name() + " " + saved_feedback.getStudent().getLast_name());
            return new ResponseEntity<>(addFeedbackResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
