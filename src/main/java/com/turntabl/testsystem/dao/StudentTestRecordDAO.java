package com.turntabl.testsystem.dao;

import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.message.GeneralAddResponse;
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
    @Autowired
    private StringToUserIdConverter stringToUserIdConverter;

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
                    studentTestRecordResponse.setTest_date(studentTestRecord.get().getTest().getTest_date());
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
                    studentTestRecordResponse.setTest_date(studentTestRecord.get().getTest().getTest_date());
                    studentTestRecordResponse.setStudent_id(studentTestRecord.get().getStudent().getStudent_id());
                    return studentTestRecordResponse;
                }).collect(Collectors.toList());
    }

    public GeneralAddResponse add(StudentTestRecord studentTestRecord) {
        GeneralAddResponse generalAddResponse = new GeneralAddResponse();
        Optional<StudentTestRecord> optionalStudentTestRecord = studentTestRecordRepository.findTestTakenByStudentIdAndTestId(studentTestRecord.getStudent().getStudent_id(), studentTestRecord.getTest().getTest_id());
        if(optionalStudentTestRecord.isEmpty()){
             studentTestRecordRepository.save(studentTestRecord);
             generalAddResponse.setMessage("success");
        }else{
            generalAddResponse.setMessage("already exists");
        }
        return  generalAddResponse;
    }

    public Boolean hasStudentTakenTest(long test_id, String student_id){
        Boolean isTaken = false;
        Optional<StudentTestRecord> optionalStudentTestRecord = studentTestRecordRepository.findTestTakenByStudentIdAndTestId(stringToUserIdConverter.convert(student_id), test_id);
        if(optionalStudentTestRecord.isPresent()){
            isTaken = true;
        }
        return isTaken;
    }
}
