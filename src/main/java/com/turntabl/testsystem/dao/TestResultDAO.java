package com.turntabl.testsystem.dao;

import com.turntabl.testsystem.message.GeneralAddResponse;
import com.turntabl.testsystem.message.StudentResultComment;
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
    public List<TestResult> getByStudentId(UUID student_id){
        return testResultRepository.findByStudentId(student_id).get();
    }
    public List<TestResult> getAllByTestId(long test_id){
        return testResultRepository.findAllByTestId(test_id).get();
    }
    public TestResult getByStudentIdTestId(UUID student_id, long test_id){
        Optional<TestResult> optionalTestResult = testResultRepository.findByStudentIdAndTestId(test_id, student_id);
        TestResult testResult = new TestResult();
        if(optionalTestResult.isPresent()){
            testResult = testResultRepository.findByStudentIdAndTestId(test_id, student_id).get();
        }
        return testResult;
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

    public GeneralAddResponse sendComment(StudentResultComment studentResultComment){
        try{
            TestResult testResult = testResultRepository.findByStudentIdAndTestId(studentResultComment.getTest_id(),studentResultComment.getStudent_id()).get();
            testResult.setComment(studentResultComment.getComment());
            testResultRepository.save(testResult);
            return new GeneralAddResponse("success");
        }catch (Exception e){
            return new GeneralAddResponse(e.getMessage());
        }
    }
}
