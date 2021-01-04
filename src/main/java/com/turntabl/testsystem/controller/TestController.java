package com.turntabl.testsystem.controller;

import com.turntabl.testsystem.dao.CourseDAO;
import com.turntabl.testsystem.dao.TestDAO;
import com.turntabl.testsystem.dao.UserDAO;
import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.message.*;
import com.turntabl.testsystem.model.Course;
import com.turntabl.testsystem.model.QuestionType;
import com.turntabl.testsystem.model.Test;
import com.turntabl.testsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TestController {
    @Autowired
    private final TestDAO testDAO;
    @Autowired
    private final CourseDAO courseDAO;
    @Autowired
    private final UserDAO userDAO;
    @Autowired
    private final StringToUserIdConverter stringToUserIdConverter;
    public TestController(TestDAO testDAO, CourseDAO courseDAO, UserDAO userDAO, StringToUserIdConverter stringToUserIdConverter) {
        this.testDAO = testDAO;
        this.courseDAO = courseDAO;
        this.userDAO = userDAO;
        this.stringToUserIdConverter = stringToUserIdConverter;
    }
    @GetMapping("/test/all")
    public ResponseEntity<List<TestResponse>> getAllTests(){
        try {
            List<TestResponse> tests = testDAO.getAll().stream()
                    .map(test -> {
                        TestResponse testResponse = new TestResponse();
                        testResponse.setTest_id(test.getTest_id());
                        testResponse.setCourse_id(test.getCourse().getCourse_id());
                        testResponse.setTest_title(test.getTest_title());
                        testResponse.setTest_rules(test.getTest_rules());
                        testResponse.setCourse_name(test.getCourse().getCourse_name());
                        testResponse.setTest_date(test.getTest_date());
                        testResponse.setTest_time_start(test.getTest_time_start());
                        testResponse.setTest_time_end(test.getTest_time_end());
                        return testResponse;
                    }).collect(Collectors.toList());
            return new ResponseEntity<>(tests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/test/get/{id}")
    public ResponseEntity<TestResponse> getTest(@PathVariable long id) {
        try {
            Test test = new Test();
            test = testDAO.get(id).get();
            TestResponse testResponse = new TestResponse();
            testResponse.setTest_id(test.getTest_id());
            testResponse.setTest_title(test.getTest_title());
            testResponse.setTest_rules(test.getTest_rules());
            testResponse.setTest_date(test.getTest_date());
            testResponse.setTest_time_start(test.getTest_time_start());
            testResponse.setTest_time_end(test.getTest_time_end());
            testResponse.setCourse_id(test.getCourse().getCourse_id());
            return new ResponseEntity<>(testResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/test/add")
    public ResponseEntity<GeneralAddResponse> addTest(@RequestBody TestRequest addTestRequest){
       try {
           User user = userDAO.get(stringToUserIdConverter.convert(addTestRequest.getUser_id())).get();
           if(!testDAO.getByTestTitle(addTestRequest.getTest_title())){
               Test testSave = new Test();
               Course course = new Course();
               course = courseDAO.get(addTestRequest.getCourse_id());
               testSave.assignCourse(course);
               testSave.setTest_title(addTestRequest.getTest_title());
               testSave.setTest_rules(addTestRequest.getTest_rule());
               testSave.setTest_date(addTestRequest.getTest_date());
               testSave.setTest_time_start(addTestRequest.getTest_time_start());
               testSave.setTest_time_end(addTestRequest.getTest_time_end());
               testSave.assignUser(user);
               switch(addTestRequest.getQuestions_type()){
                   case ("MC"):
                       testSave.setQuestionType(QuestionType.MULTIPLE_CHOICE);
                       break;
                   case("CS"):
                       testSave.setQuestionType(QuestionType.CODE_SNIPPET);
                       break;
                   case("E"):
                       testSave.setQuestionType(QuestionType.ESSAY);
                       break;
               }
               testDAO.add(testSave);
               return new ResponseEntity<>(new GeneralAddResponse("success"), HttpStatus.OK);
           }else{
               return new ResponseEntity<>(new GeneralAddResponse("duplicate test title"), HttpStatus.OK);
           }
       }catch (Exception e){
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @PostMapping("/test/update")
    public ResponseEntity<TestResponse> updateTest(@RequestBody TestRequest testRequest){
        try {
            Test test = new Test();
            TestResponse testResponse = new TestResponse();
            Course course = new Course();
            course = courseDAO.get(testRequest.getCourse_id());
            test.assignCourse(course);
            test.setTest_id(testRequest.getTest_id());
            test.setTest_title(testRequest.getTest_title());
            test.setTest_rules(testRequest.getTest_rule());
            test.setTest_date(testRequest.getTest_date());
            test.setTest_time_end(testRequest.getTest_time_end());
            test.setTest_time_start(testRequest.getTest_time_start());
            test = testDAO.update(test);
            testResponse.setTest_id(test.getTest_id());
            testResponse.setTest_title(test.getTest_title());
            testResponse.setTest_rules(test.getTest_rules());
            testResponse.setCourse_id(test.getCourse().getCourse_id());
            testResponse.setCourse_name(test.getCourse().getCourse_name());
            testResponse.setTest_date(test.getTest_date());
            testResponse.setTest_time_start(test.getTest_time_start());
            testResponse.setTest_time_end(test.getTest_time_end());
            return new ResponseEntity<>(testResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/test/delete/{id}")
    public ResponseEntity<Boolean> deleteTest(@PathVariable long id){
        try {
            Boolean check;
            Test test = new Test();
            test = testDAO.get(id).get();
            check = testDAO.delete(test);
            return new ResponseEntity<>(check, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
