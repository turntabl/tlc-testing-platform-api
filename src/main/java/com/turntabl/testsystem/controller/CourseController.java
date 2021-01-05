package com.turntabl.testsystem.controller;

import com.turntabl.testsystem.dao.CourseDAO;
import com.turntabl.testsystem.dao.UserDAO;
import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.message.CourseRequest;
import com.turntabl.testsystem.message.CourseResponse;
import com.turntabl.testsystem.message.GeneralAddResponse;
import com.turntabl.testsystem.message.CourseUpdateRequest;
import com.turntabl.testsystem.model.Course;
import com.turntabl.testsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private final CourseDAO courseDAO;
    @Autowired
    private final UserDAO userDAO;
    @Autowired
    private final StringToUserIdConverter stringToUserIdConverter;
    public CourseController(CourseDAO courseDAO, UserDAO userDAO, StringToUserIdConverter stringToUserIdConverter) {
        this.courseDAO = courseDAO;
        this.userDAO = userDAO;
        this.stringToUserIdConverter = stringToUserIdConverter;
    }
    @GetMapping("/courses/get")
    public ResponseEntity<List<CourseResponse>> getAllCourses(){
        try {
            List<CourseResponse> courses = courseDAO.getAll().stream()
                    .map(course -> {
                        CourseResponse courseResponse = new CourseResponse();
                        courseResponse.setCourseId(course.getCourse_id()) ;
                        courseResponse.setCourseName(course.getCourse_name());
                        return courseResponse;
                    }).collect(Collectors.toList());
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/courses/get/{id}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable long id){
        try {
            Course course = new Course();
            course = courseDAO.get(id);
            CourseResponse courseResponse = new CourseResponse();
            courseResponse.setCourseId(course.getCourse_id());
            courseResponse.setCourseName(course.getCourse_name());
            return new ResponseEntity<>(courseResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/course/add")
    public ResponseEntity<GeneralAddResponse> addCourse(@RequestBody CourseRequest courseRequest) {
        try {
            User user = userDAO.get(stringToUserIdConverter.convert(courseRequest.getUser_id())).get();
            if(!courseDAO.getByName(courseRequest.getCourseName())){
                Course course = new Course();
                course.setCourse_name(courseRequest.getCourseName());
                course.assignUser(user);
                courseDAO.add(course);
                return new ResponseEntity<>(new GeneralAddResponse("success"), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new GeneralAddResponse("duplicate course name"), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/course/update")
    public ResponseEntity<CourseResponse> updateCourse(@RequestBody CourseUpdateRequest courseUpdateRequest){
        try {
            Course course = new Course();
            CourseResponse courseResponse = new CourseResponse();
            course.setCourse_id(courseUpdateRequest.getCourseId());
            course.setCourse_name(courseUpdateRequest.getCourseName());
            course = courseDAO.update(course);
            courseResponse.setCourseId(course.getCourse_id());
            courseResponse.setCourseName(course.getCourse_name());
           return new ResponseEntity<>(courseResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin
    @DeleteMapping("/course/delete/{id}")
    public ResponseEntity<Boolean> deleteCourse(@PathVariable long id){
        try {
            Boolean check;
            Course course = courseDAO.get(id);
            check = courseDAO.delete(course);
            return new ResponseEntity<>(check, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
