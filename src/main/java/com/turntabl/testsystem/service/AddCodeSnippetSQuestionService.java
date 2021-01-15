package com.turntabl.testsystem.service;

import com.turntabl.testsystem.dao.*;
import com.turntabl.testsystem.helper.AddEssayQuestionsHelper;
import com.turntabl.testsystem.helper.AddStudentsCSVHelper;
import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.message.AddEssayQuestionResponse;
import com.turntabl.testsystem.message.EssayQuestionDetails;
import com.turntabl.testsystem.model.Question;
import com.turntabl.testsystem.model.Test;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AddCodeSnippetSQuestionService {
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

    public AddCodeSnippetSQuestionService(QuestionDAO questionDAO, TestDAO testDAO, OptionDAO optionDAO, ValidAnswerDAO validAnswerDAO, UserDAO userDAO, StringToUserIdConverter stringToUserIdConverter) {
        this.questionDAO = questionDAO;
        this.testDAO = testDAO;
        this.optionDAO = optionDAO;
        this.validAnswerDAO = validAnswerDAO;
        this.userDAO = userDAO;
        this.stringToUserIdConverter = stringToUserIdConverter;
    }


    public AddEssayQuestionResponse save(MultipartFile file, Long test_id) {
        Optional<Test> test = testDAO.get(test_id);
        AtomicInteger total_record_inserted = new AtomicInteger();
        AddEssayQuestionResponse addEssayQuestionResponse = new AddEssayQuestionResponse();
        List<EssayQuestionDetails> essayQuestionDetails = new ArrayList<>();
        if(AddStudentsCSVHelper.hasCSVFormat(file)){
            try {
                essayQuestionDetails = AddEssayQuestionsHelper.csvToQuestions(file.getInputStream()).stream()
                        .map(essayQuestionRequest -> {
                            EssayQuestionDetails essayQuestionDetails1 = new EssayQuestionDetails();
                            Question question = new Question();
                            question.setQuestion(StringEscapeUtils.escapeJava(essayQuestionRequest.getQuestion()));
                            question.setMark_allocated(essayQuestionRequest.getMark_allocated());
                            question.assignTest(test.get());
                            question = questionDAO.add(question);
                            essayQuestionDetails1.setQuestion(question.getQuestion());
                            essayQuestionDetails1.setQuestion_id(question.getQuestion_id());
                            essayQuestionDetails1.setMark_allocated(question.getMark_allocated());
                            essayQuestionDetails1.setTestId(question.getTestId().getTest_id());
                            return essayQuestionDetails1;
                        }).collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException("fail to store csv data: " + e.getMessage());
            }
        }
        addEssayQuestionResponse.setAtomicInteger(total_record_inserted);
        addEssayQuestionResponse.setEssayQuestionDetails(essayQuestionDetails);
        return addEssayQuestionResponse;
    }
}
