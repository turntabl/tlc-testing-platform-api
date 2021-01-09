package com.turntabl.testsystem.controller;

import com.turntabl.testsystem.dao.TestResultDAO;
import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.message.GeneralAddResponse;
import com.turntabl.testsystem.message.StudentResultComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestCommentController {
    @Autowired
    private final TestResultDAO testResultDAO;
    @Autowired
    private final StringToUserIdConverter stringToUserIdConverter;

    public TestCommentController(TestResultDAO testResultDAO, StringToUserIdConverter stringToUserIdConverter) {
        this.testResultDAO = testResultDAO;
        this.stringToUserIdConverter = stringToUserIdConverter;
    }

    @PostMapping("/student-test-comment")
    public ResponseEntity<GeneralAddResponse> sendTestComment(@RequestBody StudentResultComment studentResultComment){
            return new ResponseEntity<>(testResultDAO.sendComment(studentResultComment), HttpStatus.OK);
    }
}
