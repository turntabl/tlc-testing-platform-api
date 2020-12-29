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
    public  Test get(Long id){
        return testRepository.findById(id).get();
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
        Test testFromDatabase = new Test();
        testFromDatabase = this.get(testFromUser.getTest_id());
        testFromDatabase.setCourse(testFromUser.getCourse());
        testFromDatabase.setTest_title(testFromUser.getTest_title());
        testFromDatabase.setTest_rules(testFromUser.getTest_rules());
        testFromDatabase.setTest_date(testFromUser.getTest_date());
        testFromDatabase.setTest_time_start(testFromUser.getTest_time_start());
        testFromDatabase.setTest_time_end(testFromUser.getTest_time_end());
        return this.testRepository.save(testFromDatabase);

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
