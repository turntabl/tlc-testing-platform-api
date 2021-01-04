package com.turntabl.testsystem.service;

import com.turntabl.testsystem.dao.*;
import com.turntabl.testsystem.helper.AddMultipleChoiceQuestionsCSVHelper;
import com.turntabl.testsystem.helper.AddStudentsCSVHelper;
import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.message.AddStudentSaveResponse;
import com.turntabl.testsystem.message.StudentDetails;
import com.turntabl.testsystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AddMultipleChoiceQuestionsCSVService {
    @Autowired
    private final QuestionDAO questionDAO;

    @Autowired
    private final TestDAO testDAO;

    @Autowired
    private final OptionDAO optionDAO;

    @Autowired
    private final ValidAnswerDAO validAnswerDAO;

    @Autowired
    private final UserDAO userDAO;

    @Autowired
    private final StringToUserIdConverter stringToUserIdConverter;


    public AddMultipleChoiceQuestionsCSVService(QuestionDAO questionDAO, TestDAO testDAO, OptionDAO optionDAO, ValidAnswerDAO validAnswerDAO, UserDAO userDAO, StringToUserIdConverter stringToUserIdConverter) {
        this.questionDAO = questionDAO;
        this.testDAO = testDAO;
        this.optionDAO = optionDAO;
        this.validAnswerDAO = validAnswerDAO;
        this.userDAO = userDAO;
        this.stringToUserIdConverter = stringToUserIdConverter;
    }


    public AddStudentSaveResponse save(MultipartFile file, Long test_id) {
        Optional<Test> test = testDAO.get(test_id);
        AtomicInteger total_record_inserted = new AtomicInteger();
        AddStudentSaveResponse addStudentSaveResponse = new AddStudentSaveResponse();
        List<StudentDetails> students = new ArrayList<>();
        if(AddStudentsCSVHelper.hasCSVFormat(file)){
            try {
                AddMultipleChoiceQuestionsCSVHelper.csvToQuestions(file.getInputStream()).stream()
                        .map(questionRequest -> {
                            ValidAnswer validAnswer  = new ValidAnswer();
                            Question question = new Question();
                            question.setQuestion(questionRequest.getQuestion());
                            question.setMark_allocated(questionRequest.getMark_allocated());
                            question.assignTest(test.get());
                            question = questionDAO.add(question);
                            List<Option> options = questionRequest.getOption().stream()
                                    .map(optionRequest -> {
                                        Option option = new Option();
                                        option.setOption(optionRequest);
                                        return option;
                                    }).collect(Collectors.toList());
                            Question finalQuestionSave = question;
                            List<Option> optionToSave = options.stream()
                                    .map(option -> {
                                        option.assignQuestion(finalQuestionSave);
                                        return option;
                                    }).collect(Collectors.toList());
                            optionDAO.addAll(optionToSave);
                            Option option = optionDAO.getValidAnswer(question.getQuestion_id(), questionRequest.getValidAnswer());
                            validAnswer.setQuestion(question);
                            validAnswer.setOption(option);
                            validAnswerDAO.add(validAnswer);
                            total_record_inserted.addAndGet(1);
                            return question;
                        }).collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException("fail to store csv data: " + e.getMessage());
            }
        }
        addStudentSaveResponse.setAtomicInteger(total_record_inserted);
        addStudentSaveResponse.setStudentList(students);
        return addStudentSaveResponse;
    }
}
