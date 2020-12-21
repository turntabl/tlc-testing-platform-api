package com.turntabl.testsystem.controller;

import com.turntabl.testsystem.dao.OptionDAO;
import com.turntabl.testsystem.dao.QuestionDAO;
import com.turntabl.testsystem.dao.TestDAO;
import com.turntabl.testsystem.dao.ValidAnswerDAO;
import com.turntabl.testsystem.message.GeneralAddResponse;
import com.turntabl.testsystem.message.OptionResponse;
import com.turntabl.testsystem.message.QuestionRequest;
import com.turntabl.testsystem.message.QuestionResponse;
import com.turntabl.testsystem.model.Option;
import com.turntabl.testsystem.model.Question;
import com.turntabl.testsystem.model.Test;
import com.turntabl.testsystem.model.ValidAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
    @RequestMapping("/api")
    public class QuestionController {
        @Autowired
        private final QuestionDAO questionDAO;
        @Autowired
        private final OptionDAO optionDAO;
        @Autowired
        private final TestDAO testDAO;
        @Autowired
        private final ValidAnswerDAO validAnswerDAO;
        public QuestionController(QuestionDAO questionDAO, OptionDAO optionDAO, TestDAO testDAO, ValidAnswerDAO validAnswerDAO) {
            this.questionDAO = questionDAO;
            this.optionDAO = optionDAO;
            this.testDAO = testDAO;
            this.validAnswerDAO = validAnswerDAO;
        }
        @GetMapping("/question/{test_id}")
        public ResponseEntity<List<QuestionResponse>> getQuestionByTestId(@PathVariable long test_id) {
            try {
                 List<QuestionResponse> questionResponses = questionDAO.getQuestionsByTestId(test_id).stream()
                         .map(question1 -> {
                             QuestionResponse questionResponse =new QuestionResponse();
                             questionResponse.setOptions(question1.getOptions().stream().map(option -> {
                                 OptionResponse optionResponse = new OptionResponse();
                                 optionResponse.setOption(option.getOption());
                                 optionResponse.setOptionId(option.getOptionId());
                                 return optionResponse;
                             }).collect(Collectors.toSet()));
                             questionResponse.setQuestion(question1.getQuestion());
                             questionResponse.setQuestionId(question1.getQuestion_id());
                             questionResponse.setMark_allocated(question1.getMark_allocated());
                             return questionResponse;
                         }).collect(Collectors.toList());
                return new ResponseEntity<>(questionResponses, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @PostMapping("/question/add")
        public ResponseEntity<GeneralAddResponse> addQuestion(@RequestBody QuestionRequest addQuestionRequest){
        try {
            Question questionSave = new Question();
            ValidAnswer validAnswer  = new ValidAnswer();
            Test test = testDAO.get(addQuestionRequest.getTestId());
            questionSave.assignTest(test);
            questionSave.setMark_allocated(addQuestionRequest.getMark_allocated());
            questionSave.setQuestion(addQuestionRequest.getQuestion());
            questionSave = questionDAO.add(questionSave);
            List<Option> options = addQuestionRequest.getOption().stream()
                    .map(optionRequest -> {
                        Option option = new Option();
                        option.setOption(optionRequest);
                        return option;
                    }).collect(Collectors.toList());
            Question finalQuestionSave = questionSave;
            List<Option> optionToSave = options.stream()
                    .map(option -> {
                        option.assignQuestion(finalQuestionSave);
                        return option;
                    }).collect(Collectors.toList());
            optionDAO.addAll(optionToSave);
           Option option = optionDAO.getValidAnswer(questionSave.getQuestion_id(), addQuestionRequest.getValidAnswer());
            validAnswer.setQuestion(questionSave);
            validAnswer.setOption(option);
            validAnswerDAO.add(validAnswer);
            GeneralAddResponse generalAddResponse = new GeneralAddResponse();
            generalAddResponse.setMessage("Success");
            return new ResponseEntity<>(generalAddResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new GeneralAddResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    }
