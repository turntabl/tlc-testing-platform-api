package com.turntabl.testsystem.dao;

import com.turntabl.testsystem.model.Test;
import com.turntabl.testsystem.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class TestDAO {
    @Autowired
    private TestRepository testRepository;
    public TestDAO(){}
    public TestDAO(TestRepository testRepository) {
        this.testRepository = testRepository;
    }
    public  Optional<Test> get(Long id){
        return testRepository.findById(id);
    }
    public Boolean getByTestTitle(String test_title){
        return testRepository.findByTestTitle(test_title).isPresent();
    }
    public List<Test> addAll(List<Test> t){
        return testRepository.saveAll(t);
    }
    public List<Test> getAll() {
        return testRepository.findAll();
    }
    public Test add(Test test) {
        return testRepository.save(test);
    }
    public Test update(Test testFromUser){
        Test test;
        Optional<Test> testFromDB = testRepository.findById(testFromUser.getTest_id());
        if(testFromDB.isPresent()){
            testFromDB.get().setQuestionType(testFromUser.getQuestionType());
            testFromDB.get().setTest_time_start(testFromUser.getTest_time_start());
            testFromDB.get().setTest_time_end(testFromUser.getTest_time_end());
            testFromDB.get().setTest_date(testFromUser.getTest_date());
            testFromDB.get().setTest_title(testFromUser.getTest_title());
            testFromDB.get().setTest_rules(testFromUser.getTest_rules());
            testFromDB.get().setCourse(testFromUser.getCourse());
            test = testRepository.save(testFromDB.get());
        }else{
            test = testFromUser;
        }
        return test;
    }
    public boolean delete(Test test) {
        boolean isDeleted = false;
        Optional<Test> optionalTest = this.testRepository.findById(test.getTest_id());
        if (optionalTest.isPresent()) {
            this.testRepository.delete(optionalTest.get());
            isDeleted = true;
        }
        return isDeleted;
    }
}
