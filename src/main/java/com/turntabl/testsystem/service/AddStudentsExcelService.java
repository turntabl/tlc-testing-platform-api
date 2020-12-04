package com.turntabl.testsystem.service;

import com.turntabl.testsystem.helper.AddStudentsExcelHelper;
import com.turntabl.testsystem.model.Student;
import com.turntabl.testsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AddStudentsExcelService {

        @Autowired
        StudentRepository studentRepository;

        public void save(MultipartFile file) {
            try {
                List<Student> students = AddStudentsExcelHelper.excelToStudents(file.getInputStream());
                studentRepository.saveAll(students);
            } catch (IOException e) {
                throw new RuntimeException("fail to store excel data: " + e.getMessage());
            }
        }

        public List<Student> getStudents() {
            return studentRepository.findAll();
        }



}
