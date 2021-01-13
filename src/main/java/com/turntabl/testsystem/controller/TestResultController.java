package com.turntabl.testsystem.controller;

import com.turntabl.testsystem.dao.TestResultDAO;
import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.message.GeneralAddResponse;
import com.turntabl.testsystem.message.TestResultResponse;
import com.turntabl.testsystem.model.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TestResultController {
    @Autowired
    private final TestResultDAO testResultDAO;
    @Autowired
    private final StringToUserIdConverter stringToUserIdConverter;

    public TestResultController(TestResultDAO testResultDAO, StringToUserIdConverter stringToUserIdConverter) {
        this.testResultDAO = testResultDAO;
        this.stringToUserIdConverter = stringToUserIdConverter;
    }

    @GetMapping("/results-test/{test_id}")
    public ResponseEntity<List<TestResultResponse>> getTestResultsByTestId(@PathVariable long test_id){
        try {
            List<TestResultResponse> testResultResponse = new ArrayList<>();
            Optional<List<TestResult>> testResultsFromDB = testResultDAO.getAllByTestId(test_id);
            if(!testResultsFromDB.isEmpty()){
                testResultResponse  = testResultsFromDB.get().stream().map(testResult -> {
                    TestResultResponse testResultResponse1 = new TestResultResponse();
                    testResultResponse1.setTest_result_id(testResult.getTest_result_id());
                    testResultResponse1.setTest_grade(testResult.getTest_grade());
                    testResultResponse1.setTest_title(testResult.getTest().getTest_title());
                    testResultResponse1.setTest_mark(testResult.getTest_mark());
                    testResultResponse1.setStudent_id(testResult.getStudent().getStudent_id());
                    testResultResponse1.setStudent_name(testResult.getStudent().getFirst_name() + " " + testResult.getStudent().getLast_name());
                    testResultResponse1.setComment(testResult.getComment());
                    return  testResultResponse1;
                }).collect(Collectors.toList());
                return new ResponseEntity<>(testResultResponse, HttpStatus.OK);
            }
            return new ResponseEntity<>(testResultResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/results-student/{student_id}")
    public ResponseEntity<List<TestResultResponse>> getTestResultsByStudentId(@PathVariable String student_id){
        try {
            List<TestResultResponse> testResultResponses = new ArrayList<>();
            Optional<List<TestResult>> testResultsFromDB = testResultDAO.getByStudentId(stringToUserIdConverter.convert(student_id));
            if(!testResultsFromDB.isEmpty()){
                testResultResponses = testResultsFromDB.get().stream()
                        .map(testResult -> {
                            TestResultResponse testResultResponse =  new TestResultResponse();
                            testResultResponse.setStudent_name(testResult.getStudent().getFirst_name() + " " + testResult.getStudent().getLast_name());
                            testResultResponse.setTest_title(testResult.getTest().getTest_title());
                            testResultResponse.setTest_mark(testResult.getTest_mark());
                            testResultResponse.setTest_result_id(testResult.getTest_result_id());
                            testResultResponse.setTest_grade(testResult.getTest_grade());
                            testResultResponse.setStudent_id(testResult.getStudent().getStudent_id());
                            testResultResponse.setComment(testResult.getComment());
                            return testResultResponse;
                        }).collect(Collectors.toList());
                return new ResponseEntity<>(testResultResponses, HttpStatus.OK);
            }
            return new ResponseEntity<>(testResultResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/student/results/{student_id}/{test_id}")
    public ResponseEntity<TestResultResponse> getTestResultsByStudentId(@PathVariable String student_id, @PathVariable long test_id){
        try {
            TestResultResponse testResultResponse = new TestResultResponse();
            TestResult testResult = testResultDAO.getByStudentIdTestId(stringToUserIdConverter.convert(student_id),  test_id);
            testResultResponse.setComment(testResult.getComment());
            testResultResponse.setStudent_id(testResult.getStudent().getStudent_id());
            testResultResponse.setTest_result_id(testResult.getTest_result_id());
            testResultResponse.setTest_mark(testResult.getTest_mark());
            testResultResponse.setTest_grade(testResult.getTest_grade());
            testResultResponse.setStudent_name(testResult.getStudent().getFirst_name() + " " + testResult.getStudent().getLast_name());
            testResultResponse.setTest_title(testResult.getTest().getTest_title());
            return new ResponseEntity<>(testResultResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
