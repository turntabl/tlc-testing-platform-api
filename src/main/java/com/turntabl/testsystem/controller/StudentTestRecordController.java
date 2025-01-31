package com.turntabl.testsystem.controller;

import com.turntabl.testsystem.dao.StudentTestRecordDAO;
import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.message.StudentTestRecordResponse;
import com.turntabl.testsystem.model.StudentTestRecord;
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

@RestController
@RequestMapping("/api")
public class StudentTestRecordController {
    @Autowired
    private final StudentTestRecordDAO studentTestRecordDAO;
    @Autowired
    private final StringToUserIdConverter stringToUserIdConverter;

    public StudentTestRecordController(StudentTestRecordDAO studentTestRecordDAO, StringToUserIdConverter stringToUserIdConverter) {
        this.studentTestRecordDAO = studentTestRecordDAO;
        this.stringToUserIdConverter = stringToUserIdConverter;
    }

    @GetMapping("/test-taken/student/{student_id}")
    public ResponseEntity<List<StudentTestRecordResponse>> getAllTestTakenByStudentId(@PathVariable String student_id){
        try{
            List<StudentTestRecordResponse> studentTestRecordResponses = studentTestRecordDAO.getAllStudentTestsTakenById(stringToUserIdConverter.convert(student_id));
            if(!studentTestRecordResponses.isEmpty()){
                return new ResponseEntity<>(studentTestRecordResponses, HttpStatus.OK);
            }
            return new ResponseEntity<>(new ArrayList<>(0), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/test-taken/test/{test_id}")
    public ResponseEntity<List<StudentTestRecordResponse>> getAllTestTakenByTestId(@PathVariable long test_id){
        try{
            List<StudentTestRecordResponse> studentTestRecordResponses = studentTestRecordDAO.getAllStudentTestsTakenByTestId(test_id);
            if(!studentTestRecordResponses.isEmpty()){
                return new ResponseEntity<>(studentTestRecordResponses, HttpStatus.OK);
            }
            return new ResponseEntity<>(new ArrayList<>(0), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/test-taken/test/{test_id}/{student_id}")
    public ResponseEntity<Boolean> getStudentTestRecord(@PathVariable long test_id, @PathVariable String student_id){
        try{
            return new ResponseEntity<>(studentTestRecordDAO.hasStudentTakenTest(test_id, student_id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
