package com.turntabl.testsystem.controller;

import com.turntabl.testsystem.dao.*;
import com.turntabl.testsystem.helper.AddMultipleChoiceQuestionsCSVHelper;
import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.message.*;
import com.turntabl.testsystem.model.*;
import com.turntabl.testsystem.service.AddMultipleChoiceQuestionsCSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;
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
        @Autowired
        private final UserDAO userDAO;
        @Autowired
        private final StringToUserIdConverter stringToUserIdConverter;
        @Autowired
        private final AddMultipleChoiceQuestionsCSVService addMultipleChoiceQuestionsCSVService;
        public QuestionController(QuestionDAO questionDAO, OptionDAO optionDAO, TestDAO testDAO, ValidAnswerDAO validAnswerDAO, UserDAO userDAO, StringToUserIdConverter stringToUserIdConverter, AddMultipleChoiceQuestionsCSVService addMultipleChoiceQuestionsCSVService) {
            this.questionDAO = questionDAO;
            this.optionDAO = optionDAO;
            this.testDAO = testDAO;
            this.validAnswerDAO = validAnswerDAO;
            this.userDAO = userDAO;
            this.stringToUserIdConverter = stringToUserIdConverter;
            this.addMultipleChoiceQuestionsCSVService = addMultipleChoiceQuestionsCSVService;
        }
        @GetMapping("/question/{test_id}")
        public ResponseEntity<List<QuestionResponse>> getQuestionByTestId(@PathVariable long test_id) {
            try {
                System.out.println(test_id);
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
            Optional<Question> question = questionDAO.getByName(addQuestionRequest.getQuestion());
            Optional<Test> test = testDAO.get(addQuestionRequest.getTestId());
            User user = userDAO.get(stringToUserIdConverter.convert(addQuestionRequest.getUser_id())).get();
            if(test.get().getUser().getUser_id().equals(user.getUser_id())){
                if(question.isPresent() && test.isPresent()){
                    return new ResponseEntity<>(new GeneralAddResponse("Duplicate Question in Test"), HttpStatus.OK);
                }else{
                    Question questionSave = new Question();
                    ValidAnswer validAnswer  = new ValidAnswer();
                    questionSave.assignTest(test.get());
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
                }
            }else{
                return new ResponseEntity<>(new GeneralAddResponse("Unauthorized user"), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(new GeneralAddResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        @PostMapping(value = "/questions/upload", consumes = {"multipart/form-data"})
        public ResponseEntity<ResponseMessage> uploadFile(@RequestPart("file") MultipartFile file, @RequestParam(value = "test_id") Long test_id) {
            String message = "";
            AddQuestionsResponse addQuestionsResponse;
            if (AddMultipleChoiceQuestionsCSVHelper.hasCSVFormat(file)) {
                try {
                    addQuestionsResponse = addMultipleChoiceQuestionsCSVService.save(file, test_id);
                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage<>(message, 200, addQuestionsResponse));
                } catch (Exception e) {
                    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage<>(message, 203, null));
                }
            }
            message = "Please upload an excel or csv file!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage<>(message, 203, null));
        }
    }