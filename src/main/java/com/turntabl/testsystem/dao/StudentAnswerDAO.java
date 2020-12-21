package com.turntabl.testsystem.dao;

import com.turntabl.testsystem.model.Feedback;
import com.turntabl.testsystem.model.StudentAnswer;
import com.turntabl.testsystem.repository.StudentAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentAnswerDAO {
    @Autowired
    private StudentAnswerRepository studentAnswerRepository;
    public StudentAnswerDAO(){}
    public StudentAnswerDAO(StudentAnswerRepository studentAnswerRepository) {
        this.studentAnswerRepository = studentAnswerRepository;
    }
    public StudentAnswer get(Long id){
        return studentAnswerRepository.findById(id).get();
    }
    public List<StudentAnswer> getAllByStudentIdAndTestId(UUID id, Long test_id){
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        Optional<List<StudentAnswer>> optionalStudentAnswer = studentAnswerRepository.findAllByStudentIdAndTestId(id, test_id);
        if(optionalStudentAnswer.isPresent()){
            studentAnswers = optionalStudentAnswer.get();
        }
        return studentAnswers;
    }
    public List<StudentAnswer> getAllByQuestionIdAndTestId(Long question_id, Long test_id){
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        Optional<List<StudentAnswer>> optionalStudentAnswer = studentAnswerRepository.findAllByQuestionIdAndTestId(question_id, test_id);
        if(optionalStudentAnswer.isPresent()){
            studentAnswers = optionalStudentAnswer.get();
        }
        return studentAnswers;
    }
    public List<StudentAnswer> addAll(List<StudentAnswer> t){
        return studentAnswerRepository.saveAll(t);
    }
    public List<StudentAnswer> getAll() {
        return studentAnswerRepository.findAll();
    }
    public StudentAnswer add(StudentAnswer studentAnswer) {
        return studentAnswerRepository.save(studentAnswer);
    }
    public StudentAnswer update(StudentAnswer studentAnswer){
       StudentAnswer studentAnswer1 = studentAnswerRepository.findById(studentAnswer.getStudent_answer_id()).get();
        studentAnswer1.setStudent_answer(studentAnswer.getStudent_answer());
        return this.studentAnswerRepository.save(studentAnswer1);
    }
    public boolean delete(StudentAnswer studentAnswer) {
        boolean isDeleted = false;
        Optional<StudentAnswer> optionalStudentAnswer = this.studentAnswerRepository.findById(studentAnswer.getStudent_answer_id());
        if (optionalStudentAnswer.isPresent()) {
            this.studentAnswerRepository.delete(optionalStudentAnswer.get());
            isDeleted = true;
        }
        return isDeleted;
    }
}
