package com.turntabl.testsystem.dao;

import com.turntabl.testsystem.model.TestResult;
import com.turntabl.testsystem.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TestResultDAO {
    @Autowired
    private TestResultRepository testResultRepository;
    public TestResultDAO(){}
    public TestResultDAO(TestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }
    public TestResult get(Long id){
        return testResultRepository.findById(id).get();
    }
    public TestResult getByStudentId(UUID student_id){
        return testResultRepository.findByStudentId(student_id).get();
    }
    public List<TestResult> getAllByTestId(long test_id){
        return testResultRepository.findAllByTestId(test_id).get();
    }
    public List<TestResult> addAll(List<TestResult> t){
        return testResultRepository.saveAll(t);
    }
    public List<TestResult> getAll() {
        return testResultRepository.findAll();
    }
    public TestResult add(TestResult testResult) {
        return testResultRepository.save(testResult);
    }
    public TestResult update(TestResult testResult){
        TestResult testResult1 = testResultRepository.findById(testResult.getTest_result_id()).get();
        testResult1.setTest_mark(testResult.getTest_mark());
        testResult1.setTest_grade(testResult.getTest_grade());
        return this.testResultRepository.save(testResult1);

    }
    public boolean delete(TestResult testResult) {
        boolean isDeleted = false;
        Optional<TestResult> optionalTest = this.testResultRepository.findById(testResult.getTest_result_id());
        if (optionalTest.isPresent()) {
            this.testResultRepository.delete(optionalTest.get());
            isDeleted = true;
        }
        return isDeleted;
    }
}
