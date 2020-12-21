package com.turntabl.testsystem.model;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "student_answers")
public class StudentAnswer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_answer_id")
    private long student_answer_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @Column(name = "student_answer")
    private String student_answer;
    @Column(name = "comment")
    private String comment;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_created")
    private Date dateAnswered;

    public StudentAnswer(long student_answer_id, Question question, Test test, Student student, String student_answer, String comment, Date dateAnswered) {
        this.student_answer_id = student_answer_id;
        this.question = question;
        this.test = test;
        this.student = student;
        this.student_answer = student_answer;
        this.comment = comment;
        this.dateAnswered = dateAnswered;
    }

    public StudentAnswer() {
    }

    public long getStudent_answer_id() {
        return student_answer_id;
    }

    public void setStudent_answer_id(long student_answer_id) {
        this.student_answer_id = student_answer_id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStudent_answer() {
        return student_answer;
    }

    public void setStudent_answer(String student_answer) {
        this.student_answer = student_answer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateAnswered() {
        return dateAnswered;
    }

    public void setDateAnswered(Date dateAnswered) {
        this.dateAnswered = dateAnswered;
    }

    public void assignTest(Test test){
        this.test = test;
        this.test.addStudentAnswer(this);
    }

    public void assignStudent(Student student){
        this.student = student;
        this.student.addStudentAnswer(this);
    }

    public void assignQuestion(Question question){
        this.question = question;
    }
}
