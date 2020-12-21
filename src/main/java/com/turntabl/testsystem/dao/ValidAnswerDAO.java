package com.turntabl.testsystem.dao;

import com.turntabl.testsystem.model.ValidAnswer;
import com.turntabl.testsystem.repository.ValidAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class ValidAnswerDAO {
    @Autowired
    private ValidAnswerRepository validAnswerRepository;
    public ValidAnswerDAO(){}
    public ValidAnswerDAO(ValidAnswerRepository validAnswerRepository) {
        this.validAnswerRepository = validAnswerRepository;
    }
    public ValidAnswer get(Long id){
        return validAnswerRepository.findById(id).get();
    }
    public ValidAnswer getByQuestionId(Long question_id){
        return validAnswerRepository.findByQuestionId(question_id).get();
    }
    public List<ValidAnswer> addAll(List<ValidAnswer> t){
        return validAnswerRepository.saveAll(t);
    }
    public List<ValidAnswer> getAll() {
        return validAnswerRepository.findAll();
    }
    public ValidAnswer add(ValidAnswer validAnswer) {
        return validAnswerRepository.save(validAnswer);
    }
}
