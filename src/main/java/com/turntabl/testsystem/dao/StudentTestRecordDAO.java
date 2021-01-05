package com.turntabl.testsystem.dao;

import com.turntabl.testsystem.message.StudentTestRecordResponse;
import com.turntabl.testsystem.model.StudentTestRecord;
import com.turntabl.testsystem.repository.StudentTestRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


public class StudentTestRecordDAO {
    @Autowired
    private  StudentTestRecordRepository studentTestRecordRepository;

    public StudentTestRecordDAO() {
    }

    public StudentTestRecord get(long id) {
        StudentTestRecord studentTestRecord = new StudentTestRecord();
        Optional<StudentTestRecord> optionalStudentTestRecord = studentTestRecordRepository.findById(id);
        if (optionalStudentTestRecord.isPresent()) {
            studentTestRecord = optionalStudentTestRecord.get();
        }
        return studentTestRecord;
    }

    public List<StudentTestRecordResponse> getAllStudentTestsTakenById(UUID student_id){
        return studentTestRecordRepository.findAllTestsTakenByStudentId(student_id).stream()
                .map(studentTestRecord -> {
                    StudentTestRecordResponse studentTestRecordResponse = new StudentTestRecordResponse();
                    studentTestRecordResponse.setStudent_test_record_id(studentTestRecord.get().getStudent_test_record_id());
                    studentTestRecordResponse.setTest_title(studentTestRecord.get().getTest().getTest_title());
                    studentTestRecordResponse.setTest_id(studentTestRecord.get().getTest().getTest_id());
                    studentTestRecordResponse.setStudent_id(studentTestRecord.get().getStudent().getStudent_id());
                    return studentTestRecordResponse;
                }).collect(Collectors.toList());
    }

    public List<StudentTestRecordResponse> getAllStudentTestsTakenByTestId(long test_id){
        return studentTestRecordRepository.findAllTestsTakenByTestId(test_id).stream()
                .map(studentTestRecord -> {
                    StudentTestRecordResponse studentTestRecordResponse = new StudentTestRecordResponse();
                    studentTestRecordResponse.setStudent_test_record_id(studentTestRecord.get().getStudent_test_record_id());
                    studentTestRecordResponse.setTest_title(studentTestRecord.get().getTest().getTest_title());
                    studentTestRecordResponse.setTest_id(studentTestRecord.get().getTest().getTest_id());
                    studentTestRecordResponse.setStudent_id(studentTestRecord.get().getStudent().getStudent_id());
                    return studentTestRecordResponse;
                }).collect(Collectors.toList());
    }

    public StudentTestRecord add(StudentTestRecord studentTestRecord) {
        return studentTestRecordRepository.save(studentTestRecord);
    }
}
