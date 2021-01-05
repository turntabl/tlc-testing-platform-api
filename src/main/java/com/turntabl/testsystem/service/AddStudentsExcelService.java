package com.turntabl.testsystem.service;
import com.turntabl.testsystem.dao.StudentDAO;
import com.turntabl.testsystem.helper.AddStudentsCSVHelper;
import com.turntabl.testsystem.helper.AddStudentsExcelHelper;
import com.turntabl.testsystem.message.AddStudentSaveResponse;
import com.turntabl.testsystem.message.StudentDetails;
import com.turntabl.testsystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
@Service
public class AddStudentsExcelService {
        @Autowired
        private final StudentDAO studentDAO;
    public AddStudentsExcelService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
    public AddStudentSaveResponse save(MultipartFile file) {
        AtomicInteger total_record_inserted = new AtomicInteger();
        AddStudentSaveResponse addStudentSaveResponse = new AddStudentSaveResponse();
        List<StudentDetails> students = new ArrayList<>();
            if(AddStudentsExcelHelper.hasExcelFormat(file)) {
                try {
                    AddStudentsExcelHelper.excelToStudents(file.getInputStream()).stream()
                        .map(student -> {if(studentDAO.findByEmail(student.getEmail()).isEmpty()){
                            StudentDetails studentDetails = new StudentDetails();
                            studentDAO.add(student);
                            studentDetails.setEmail(student.getEmail());
                            studentDetails.setFirst_name(student.getFirst_name());
                            studentDetails.setStudent_id(student.getStudent_id());
                            studentDetails.setLast_name(student.getLast_name());
                            students.add(studentDetails);
                            total_record_inserted.addAndGet(1);
                        }
                        return student;
                        }).collect(Collectors.toList());
                } catch (IOException e) {
                    throw new RuntimeException("fail to store excel data: " + e.getMessage());
                }
            }else if(AddStudentsCSVHelper.hasCSVFormat(file)){
                try {
                    AddStudentsCSVHelper.csvToStudents(file.getInputStream()).stream()
                            .map(student -> {if(studentDAO.findByEmail(student.getEmail()).isEmpty()){
                                StudentDetails studentDetails = new StudentDetails();
                                studentDAO.add(student);
                                studentDetails.setEmail(student.getEmail());
                                studentDetails.setFirst_name(student.getFirst_name());
                                studentDetails.setStudent_id(student.getStudent_id());
                                studentDetails.setLast_name(student.getLast_name());
                                students.add(studentDetails);
                                total_record_inserted.addAndGet(1);
                            }
                                return student;
                            }).collect(Collectors.toList());
                } catch (IOException e) {
                    throw new RuntimeException("fail to store csv data: " + e.getMessage());
                }
            }
            addStudentSaveResponse.setAtomicInteger(total_record_inserted);
            addStudentSaveResponse.setStudentList(students);
            return addStudentSaveResponse;
        }
        public List<StudentDetails> getStudents() {
            return studentDAO.getAll()
                    .stream()
                    .map(student -> {
                                StudentDetails studentDetails = new StudentDetails();
                                studentDetails.setEmail(student.getEmail());
                                studentDetails.setFirst_name(student.getFirst_name());
                                studentDetails.setStudent_id(student.getStudent_id());
                                studentDetails.setLast_name(student.getLast_name());
                                return studentDetails;
                    }
                    ).collect(Collectors.toList());
        }
}
