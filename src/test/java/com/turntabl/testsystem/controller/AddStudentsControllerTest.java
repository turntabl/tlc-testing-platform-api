package com.turntabl.testsystem.controller;

import com.turntabl.testsystem.configuration.WebSecurityConfig;
import com.turntabl.testsystem.dao.StudentDAO;
import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.model.Student;
import com.turntabl.testsystem.model.Students;
import com.turntabl.testsystem.repository.StudentRepository;
import com.turntabl.testsystem.service.AddStudentsExcelService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;



@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AddStudentsControllerTest {

    @InjectMocks
    AddStudentsController addStudentsController;


    @InjectMocks
    StringToUserIdConverter stringToUserIdConverter;

    @Mock
    StudentDAO studentDAO;
    private java.util.Optional optional;
    AddStudentsExcelService fileService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void uploadFile() throws Exception {

    }

    @Test
    void getAllStudents() {
        // given
        Student student = new Student("William", "Kwakye", "william@gmail.com");
        Student student1 = new Student("William", "Kwakye", "william@gmail.com");
        Student student2 = new Student("William", "Kwakye", "william@gmail.com");
        Students students = new Students();
        students.setStudentList(Arrays.asList(student, student1, student2));

        when(studentDAO.getAll()).thenReturn(students.getStudentList());

        // when


        // then
        assertThat(studentDAO.getAll().size()).isEqualTo(3);

        assertThat(studentDAO.getAll().get(0).getFirst_name())
                .isEqualTo(student.getFirst_name());

        assertThat(studentDAO.getAll().get(1).getFirst_name())
                .isEqualTo(student1.getFirst_name());

        assertThat(studentDAO.getAll().get(2).getFirst_name())
                .isEqualTo(student2.getFirst_name());
    }

    @Test
    void getStudentById() {

        // given
        Student student = new Student(stringToUserIdConverter.convert("dc1e6d1b-6551-4a36-8c97-87ff831d154b"),"William", "Kwakye", "william@gmail.com");
        Student student1 = new Student(stringToUserIdConverter.convert("1ceda636-cc55-4ed8-8c23-018718e51e07"),"William", "Kwakye", "william@gmail.com");
        Student student2 = new Student(stringToUserIdConverter.convert("e85ef91e-789d-4d2c-a8d9-65fabf11f5df"),"William", "Kwakye", "william@gmail.com");
        Students students = new Students();
        students.setStudentList(Arrays.asList(student, student1, student2));

        when(studentDAO.get(stringToUserIdConverter.convert("dc1e6d1b-6551-4a36-8c97-87ff831d154b"))).thenReturn((students.getStudentById(stringToUserIdConverter.convert("dc1e6d1b-6551-4a36-8c97-87ff831d154b"))));


        assertThat(studentDAO.get(stringToUserIdConverter.convert("dc1e6d1b-6551-4a36-8c97-87ff831d154b")).getFirst_name())
                .isEqualTo(student.getFirst_name());
    }
}