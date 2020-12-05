package com.turntabl.testsystem.service;

import com.turntabl.testsystem.dao.StudentDAO;
import com.turntabl.testsystem.helper.AddStudentsCSVHelper;
import com.turntabl.testsystem.helper.AddStudentsExcelHelper;
import com.turntabl.testsystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AddStudentsExcelService {

        @Autowired
       private final StudentDAO studentDAO;

    public AddStudentsExcelService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void save(MultipartFile file) {
            if(AddStudentsExcelHelper.hasExcelFormat(file)) {
                try {
                    List<Student> students = AddStudentsExcelHelper.excelToStudents(file.getInputStream());
                    studentDAO.addAll(students);
                } catch (IOException e) {
                    throw new RuntimeException("fail to store excel data: " + e.getMessage());
                }
            }else if(AddStudentsCSVHelper.hasCSVFormat(file)){
                try {
                    List<Student> students = AddStudentsCSVHelper.csvToStudents(file.getInputStream());
                    studentDAO.addAll(students);
                } catch (IOException e) {
                    throw new RuntimeException("fail to store csv data: " + e.getMessage());
                }
            }
        }

        public List<Student> getStudents() {
            return studentDAO.getAll();
        }

}
