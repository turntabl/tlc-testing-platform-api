package com.turntabl.testsystem.controller;
import com.turntabl.testsystem.dao.StudentFeedbackDAO;
import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.model.Feedback;
import com.turntabl.testsystem.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.UUID;
import java.util.function.Supplier;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@SpringBootTest
@AutoConfigureMockMvc
class StudentFeedbackControllerTest {
    @Autowired
    MockMvc mockMvc;
    @InjectMocks
    StudentFeedbackController studentFeedbackController;
    @InjectMocks
    StringToUserIdConverter stringToUserIdConverter;
    @Mock
    StudentFeedbackDAO feedbackDAO;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @BeforeEach
    void setUp() {
    }
    @AfterEach
    void tearDown() {
    }
    private final Supplier<UUID> uuidSupplier = UUID::randomUUID;
    public UUID getUuid() {
        return uuidSupplier.get();
    }
    @Test
    void getFeedbackById() {
        Student student1 = new Student(getUuid(),"William", "Kwakye", "william@gmail.com");
        Feedback feedback = new Feedback(1,student1,"this is test feedback");
        feedback.assignToStudent(student1);
        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList.add(feedback);
        when(feedbackDAO.get((long) 1)).thenReturn((feedbackList.get(0)));
        assertThat(feedbackDAO.get((long) 1).getStudent().getFirst_name())
                .isEqualTo(student1.getFirst_name());
    }
    @Test
    void getFeedbackByStudentId() {
        Student student1 = new Student(getUuid(),"William", "Kwakye", "william@gmail.com");
        Feedback feedback = new Feedback(1,student1,"this is test feedback");
        feedback.assignToStudent(student1);
        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList.add(feedback);
        when(feedbackDAO.getByStudent(student1.getStudent_id())).thenReturn((feedbackList.get(0)));
        assertThat(feedbackDAO.getByStudent(student1.getStudent_id()).getFeedback()).isEqualTo(feedback.getFeedback());
        assertThat(feedbackDAO.getByStudent(student1.getStudent_id()).getStudent().getFirst_name())
                .isEqualTo(student1.getFirst_name());
    }
    @Test
    void addFeedback() {
        Student student1 = new Student(getUuid(),"William", "Kwakye", "william@gmail.com");
        Feedback feedback = new Feedback(1,student1,"this is test feedback");
        feedback.assignToStudent(student1);
        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList.add(feedback);
        when(feedbackDAO.add(feedback)).thenReturn((feedbackList.get(0)));
        assertThat(feedbackDAO.add(feedback).getStudent().getFirst_name())
                .isEqualTo(student1.getFirst_name());
    }
    @Test
    void getAllFeedbacks() {
        // given
        Student student1 = new Student("William", "Kwakye", "william@gmail.com");
        Feedback feedback = new Feedback(1,student1,"this is test feedback");
        feedback.assignToStudent(student1);
        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList.add(feedback);
        when(feedbackDAO.getAll()).thenReturn(feedbackList);
        // then
        assertThat(feedbackDAO.getAll().size()).isEqualTo(1);
        assertThat(feedbackDAO.getAll().get(0).getFeedback())
                .isEqualTo(feedback.getFeedback());
    }
}