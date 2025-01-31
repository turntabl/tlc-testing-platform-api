package com.turntabl.testsystem.dao;

import com.turntabl.testsystem.message.GeneralAddResponse;
import com.turntabl.testsystem.model.Course;
import com.turntabl.testsystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class CourseDAO {
    @Autowired
    private CourseRepository courseRepository;
    public CourseDAO(){}
    public CourseDAO(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public Course get(Long id){
        return courseRepository.findById(id).get();
    }
    public Boolean getByName(String course_name){
        return courseRepository.findByCourseName(course_name).isPresent();
    }
    public List<Course> addAll(List<Course> t){
        return courseRepository.saveAll(t);
    }
    public List<Course> getAll() {
        return courseRepository.findAll();
    }
    public Course add(Course course) {
        return courseRepository.save(course);
    }
    public Course update(Course courseFromUser){
        Course courseFromDatabase = new Course();
        courseFromDatabase = this.get(courseFromUser.getCourse_id());
        courseFromDatabase.setCourse_name(courseFromUser.getCourse_name());
        return this.courseRepository.save(courseFromDatabase);
    }
    public GeneralAddResponse delete(Course course) {
        GeneralAddResponse generalAddResponse = new GeneralAddResponse();
        Optional<Course> optionalUser = courseRepository.findById(course.getCourse_id());
        if (optionalUser.isPresent()) {
            courseRepository.delete(optionalUser.get());
            generalAddResponse.setMessage("success");
        }else{
            generalAddResponse.setMessage("failed");
        }
        return generalAddResponse;
    }
}
