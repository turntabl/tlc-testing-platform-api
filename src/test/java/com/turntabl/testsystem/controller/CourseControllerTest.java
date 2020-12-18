package com.turntabl.testsystem.controller;

import com.turntabl.testsystem.dao.CourseDAO;
import com.turntabl.testsystem.dao.StudentFeedbackDAO;
import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.model.Course;
import com.turntabl.testsystem.model.Feedback;
import com.turntabl.testsystem.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerTest {
    @Autowired
    MockMvc mockMvc;
    @InjectMocks
    CourseController courseController;
    @Mock
    CourseDAO courseDAO;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void getAllCourses() {
        // given
        Course course = new Course();
        Course course1 = new Course();
        course.setCourse_name("Java");
        course1.setCourse_name("Programming");
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        courses.add(course1);
        when(courseDAO.getAll()).thenReturn(courses);
        // then
        assertThat(courseDAO.getAll().size()).isEqualTo(2);
        assertThat(courseDAO.getAll().get(0).getCourse_name())
                .isEqualTo(course.getCourse_name());
    }

    @Test
    void getCourse() {
        // given
        Course course = new Course();
        Course course1 = new Course();
        course.setCourse_name("Java");
        course1.setCourse_name("Programming");
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        courses.add(course1);
        when(courseDAO.get((long) 1)).thenReturn(courses.get(0));
        // then
        assertThat(courseDAO.get((long) 1).getCourse_name()).isEqualTo(course.getCourse_name());
    }

    @Test
    void addCourse() {
        // given
        Course course = new Course();
        Course course1 = new Course();
        course.setCourse_name("Java");
        course1.setCourse_name("Programming");
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        courses.add(course1);
        when(courseDAO.add(course)).thenReturn(courses.get(0));
        // then
        assertThat(courseDAO.add(course)).isEqualTo(course.getCourse_name());
    }

    @Test
    void updateCourse() {
    }

    @Test
    void deleteCourse() {
    }
}