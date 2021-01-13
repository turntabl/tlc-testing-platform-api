package com.turntabl.testsystem.controller;

import com.turntabl.testsystem.dao.*;
import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.message.AnswerResponse;
import com.turntabl.testsystem.message.GeneralAddResponse;
import com.turntabl.testsystem.message.StudentAnswerRequest;
import com.turntabl.testsystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentAnswerController {
    @Autowired
    private final StringToUserIdConverter stringToUserIdConverter;
    @Autowired
    private final StudentAnswerDAO studentAnswerDAO;
    @Autowired
    private final QuestionDAO questionDAO;
    @Autowired
    private final TestDAO testDAO;
    @Autowired
    private final StudentDAO studentDAO;
    @Autowired
    private final ValidAnswerDAO validAnswerDAO;
    @Autowired
    private final TestResultDAO testResultDAO;
    @Autowired
    private final StudentTestRecordDAO studentTestRecordDAO;
    public StudentAnswerController(StringToUserIdConverter stringToUserIdConverter, StudentAnswerDAO studentAnswerDAO, QuestionDAO questionDAO, TestDAO testDAO, StudentDAO studentDAO, ValidAnswerDAO validAnswerDAO, TestResultDAO testResultDAO, StudentTestRecordDAO studentTestRecordDAO) {
        this.stringToUserIdConverter = stringToUserIdConverter;
        this.studentAnswerDAO = studentAnswerDAO;
        this.questionDAO = questionDAO;
        this.testDAO = testDAO;
        this.studentDAO = studentDAO;
        this.validAnswerDAO = validAnswerDAO;
        this.testResultDAO = testResultDAO;
        this.studentTestRecordDAO = studentTestRecordDAO;
    }
    @GetMapping("/answers/get-by-student/{test_id}/{student_id}")
    public ResponseEntity<List<AnswerResponse>> getAllAnswersByStudent(@PathVariable Long test_id, @PathVariable String student_id ){
        try{
            List<AnswerResponse> answerResponses = new ArrayList<>();
            List<StudentAnswer> studentAnswerList = studentAnswerDAO.getAllByStudentIdAndTestId(stringToUserIdConverter.convert(student_id), test_id);
            if(!studentAnswerList.isEmpty()){
                answerResponses = studentAnswerList
                        .stream()
                        .map(studentAnswer -> {
                            AnswerResponse answerResponse = new AnswerResponse();
                            answerResponse.setStudent_answer(studentAnswer.getStudent_answer());
                            answerResponse.setQuestion(studentAnswer.getQuestion().getQuestion());
                            answerResponse.setStudent_answer_id(studentAnswer.getStudent_answer_id());
                            return  answerResponse;
                        })
                        .collect(Collectors.toList());
                return new ResponseEntity<>(answerResponses, HttpStatus.OK);
            }
            return new ResponseEntity<>(answerResponses, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/answers/get-by-question/{test_id}/{question_id}")
    public  ResponseEntity<List<AnswerResponse>> getAllAnswersByQuestionId(@PathVariable Long test_id, @PathVariable Long question_id){
        try{
            List<AnswerResponse> answerResponses = studentAnswerDAO.getAllByQuestionIdAndTestId(question_id, test_id)
                    .stream()
                    .map(studentAnswer -> {
                        AnswerResponse answerResponse = new AnswerResponse();
                        answerResponse.setStudent_answer_id(studentAnswer.getStudent_answer_id());
                        answerResponse.setStudent_answer(studentAnswer.getStudent_answer());
                        answerResponse.setQuestion(studentAnswer.getQuestion().getQuestion());
                        return answerResponse;
                    })
                    .collect(Collectors.toList());
            return new ResponseEntity<>(answerResponses, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/test-answer")
    public ResponseEntity<GeneralAddResponse> submitAnswers(@RequestBody StudentAnswerRequest answers){
        try{
            StudentTestRecord studentTestRecord = new StudentTestRecord();
            Student student = studentDAO.get(stringToUserIdConverter.convert(answers.getStudent_id()));
            studentTestRecord.assignStudent(student);
            Optional<Test> test = testDAO.get(answers.getTest_id());
            studentTestRecord.assignTest(test.get());
            List<StudentAnswer> studentAnswerList;
            List<StudentAnswer> studentAnswers = new ArrayList<>();
            String question_type = testDAO.get(answers.getTest_id()).get().getQuestionType().getCode();
            switch (question_type){
                case ("MC"):
                    studentAnswers = answers.getAnswers().stream().map(
                            answer -> {
                                StudentAnswer studentAnswer = new StudentAnswer();
                                studentAnswer.assignStudent(student);
                                studentAnswer.assignTest(test.get());
                                studentAnswer.assignQuestion(questionDAO.get(answer.getQuestion_id()));
                                if(answer.getOption_id() == (validAnswerDAO.getByQuestionId(answer.getQuestion_id()).getOption().getOptionId())){
                                    studentAnswer.setAnswer_mark(questionDAO.get(answer.getQuestion_id()).getMark_allocated());
                                }else{
                                    studentAnswer.setAnswer_mark(0.0);
                                }
                                studentAnswer.setStudent_answer(answer.getAnswer());
                                return studentAnswer;
                            }
                    ).collect(Collectors.toList());
                    break;
                case("E"):
                case("CS"):
                    studentAnswers = answers.getAnswers().stream().map(
                            answer -> {
                                StudentAnswer studentAnswer = new StudentAnswer();
                                studentAnswer.assignStudent(studentDAO.get(stringToUserIdConverter.convert(answers.getStudent_id())));
                                studentAnswer.assignTest(testDAO.get(answers.getTest_id()).get());
                                studentAnswer.assignQuestion(questionDAO.get(answer.getQuestion_id()));
                                studentAnswer.setAnswer_mark(0.0);
                                studentAnswer.setStudent_answer(answer.getAnswer());
                                return studentAnswer;
                            }
                    ).collect(Collectors.toList());
                    break;
            }
                            try{
                                studentAnswerList = studentAnswerDAO.addAll(studentAnswers);
                                if(question_type.matches("MC")){
                                    TestResult testResult = new TestResult();
                                   Double totalMark = 0.0;
                                    for (StudentAnswer sa: studentAnswerList
                                         ) {
                                        totalMark+=sa.getAnswer_mark();
                                    }
                                    testResult.assignTest(test.get());
                                    testResult.setTest_mark(totalMark);
                                    testResult.assignStudent(student);
                                    if(totalMark > 89 && totalMark <101){
                                        testResult.setTest_grade('A');
                                    }else if(totalMark > 79 && totalMark < 90){
                                        testResult.setTest_grade('B');
                                    }else if(totalMark > 69 && totalMark < 80){
                                        testResult.setTest_grade('C');
                                    }else if(totalMark > 59 && totalMark < 70){
                                        testResult.setTest_grade('D');
                                    }else if(totalMark > 49 && totalMark < 60){
                                        testResult.setTest_grade('E');
                                    }else if(totalMark < 49){
                                        testResult.setTest_grade('F');
                                    }
                                    testResultDAO.add(testResult);
                                    studentTestRecordDAO.add(studentTestRecord);
                                }
                                return new ResponseEntity<>(new GeneralAddResponse("success"), HttpStatus.OK);
                            }catch (Exception e){
                                return new ResponseEntity<>(new GeneralAddResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
                            }
        }catch (Exception e){
            return new ResponseEntity<>(new GeneralAddResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
