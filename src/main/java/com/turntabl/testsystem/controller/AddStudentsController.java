package com.turntabl.testsystem.controller;


import com.turntabl.testsystem.dao.StudentDAO;
import com.turntabl.testsystem.helper.AddStudentsCSVHelper;
import com.turntabl.testsystem.helper.AddStudentsExcelHelper;
import com.turntabl.testsystem.message.ResponseMessage;
import com.turntabl.testsystem.model.Student;
import com.turntabl.testsystem.service.AddStudentsExcelService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class AddStudentsController {

    @Autowired
    AddStudentsExcelService fileService;





    @Autowired
    private final StudentDAO studentDAO;

    public AddStudentsController(StudentDAO studentDAO) {

        this.studentDAO = studentDAO;
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseMessage> uploadFile(@RequestPart("file") MultipartFile file) {
        String message = "";
        List<Student> students = new ArrayList<>();

        if (AddStudentsExcelHelper.hasExcelFormat(file) || AddStudentsCSVHelper.hasCSVFormat(file)) {
            try {
                students = fileService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, 200, students.size()));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, 203, 0));
            }
        }

        message = "Please upload an excel or csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message, 203, 0));
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = fileService.getStudents();

            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable UUID id) {
            return new ResponseEntity<>(studentDAO.get(id), HttpStatus.OK);
    }

    @GetMapping("/student/find/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        return new ResponseEntity<>(studentDAO.getByEmail(email), HttpStatus.OK);
    }

}
