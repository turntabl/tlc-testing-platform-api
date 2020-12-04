package com.turntabl.testsystem.controller;


import com.turntabl.testsystem.helper.AddStudentsExcelHelper;
import com.turntabl.testsystem.message.ResponseMessage;
import com.turntabl.testsystem.model.Student;
import com.turntabl.testsystem.repository.StudentRepository;
import com.turntabl.testsystem.service.AddStudentsExcelService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class AddStudentsExcelController{

    @Autowired
    AddStudentsExcelService fileService;


    @Autowired
    private final StudentRepository studentRepository;

    public AddStudentsExcelController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    @ExceptionHandler({FileUploadException.class})
    public ResponseEntity<ResponseMessage> uploadFile(@RequestPart("file") MultipartFile file) {
        String message = "";

        if (AddStudentsExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, 200));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, 203));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message, 203));
    }

    @GetMapping("/students")
    @ExceptionHandler({FileUploadException.class})
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


            Optional<Student> students = studentRepository.findById(id);

            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(students.get(), HttpStatus.OK);

    }

}
