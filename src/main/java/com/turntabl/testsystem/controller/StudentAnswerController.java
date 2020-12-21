package com.turntabl.testsystem.controller;

import com.turntabl.testsystem.dao.*;
import com.turntabl.testsystem.message.AnswerResponse;
import com.turntabl.testsystem.message.GeneralAddResponse;
import com.turntabl.testsystem.message.StudentAnswerRequest;
import com.turntabl.testsystem.model.StudentAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentAnswerController {
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
    public StudentAnswerController(StudentAnswerDAO studentAnswerDAO, QuestionDAO questionDAO, TestDAO testDAO, StudentDAO studentDAO, ValidAnswerDAO validAnswerDAO) {
        this.studentAnswerDAO = studentAnswerDAO;
        this.questionDAO = questionDAO;
        this.testDAO = testDAO;
        this.studentDAO = studentDAO;
        this.validAnswerDAO = validAnswerDAO;
    }
    @GetMapping("/answers/get-by-student/{test_id}/{student_id}")
    public ResponseEntity<List<AnswerResponse>> getAllAnswersByStudent(@PathVariable Long test_id, @PathVariable UUID student_id ){
        try{
            List<AnswerResponse> answerResponses = studentAnswerDAO.getAllByStudentIdAndTestId(student_id, test_id)
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
    @PostMapping("/multiple-choice/answers")
    public ResponseEntity<GeneralAddResponse> submitAnswers(@RequestBody StudentAnswerRequest answers){
        try{
                List<StudentAnswer> studentAnswers = answers.getAnswers().stream().map(
                        answer -> {
                            StudentAnswer studentAnswer = new StudentAnswer();
                            studentAnswer.assignStudent(studentDAO.get(answers.getStudent_id()));
                            studentAnswer.assignTest(testDAO.get(answers.getTest_id()));
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
                            try{
                                studentAnswerDAO.addAll(studentAnswers);
                                return new ResponseEntity<>(new GeneralAddResponse("success"), HttpStatus.OK);
                            }catch (Exception e){
                                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
