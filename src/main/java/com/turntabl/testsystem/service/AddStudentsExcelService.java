package com.turntabl.testsystem.service;

import com.turntabl.testsystem.dao.StudentDAO;
import com.turntabl.testsystem.helper.AddStudentsCSVHelper;
import com.turntabl.testsystem.helper.AddStudentsExcelHelper;
import com.turntabl.testsystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddStudentsExcelService {

        @Autowired
       private final StudentDAO studentDAO;

    public AddStudentsExcelService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public List<Student> save(MultipartFile file) {
        List<Student> studentList = new ArrayList<>();
            if(AddStudentsExcelHelper.hasExcelFormat(file)) {
                try {
                    studentList =  AddStudentsExcelHelper.excelToStudents(file.getInputStream()).stream()
                        .map(student -> {if(!studentDAO.findByEmail(student.getEmail())){
                            studentDAO.add(student);
                        }
                        return student;
                        }).collect(Collectors.toList());

                } catch (IOException e) {
                    throw new RuntimeException("fail to store excel data: " + e.getMessage());
                }
            }else if(AddStudentsCSVHelper.hasCSVFormat(file)){
                try {
                    studentList =  AddStudentsCSVHelper.csvToStudents(file.getInputStream()).stream()
                            .map(student -> {if(!studentDAO.findByEmail(student.getEmail())){
                                studentDAO.add(student);
                            }
                                return student;
                            }).collect(Collectors.toList());
                } catch (IOException e) {
                    throw new RuntimeException("fail to store csv data: " + e.getMessage());
                }
            }

            return studentList;
        }

        public List<Student> getStudents() {
            return studentDAO.getAll();
        }

}
