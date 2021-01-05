package com.turntabl.testsystem.controller;
import com.turntabl.testsystem.dao.StudentDAO;
import com.turntabl.testsystem.helper.AddStudentsCSVHelper;
import com.turntabl.testsystem.helper.AddStudentsExcelHelper;
import com.turntabl.testsystem.message.AddStudentSaveResponse;
import com.turntabl.testsystem.message.ResponseMessage;
import com.turntabl.testsystem.message.StudentDetails;
import com.turntabl.testsystem.model.Student;
import com.turntabl.testsystem.service.AddStudentsExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
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
        AddStudentSaveResponse addStudentSaveResponse;
        if (AddStudentsExcelHelper.hasExcelFormat(file) || AddStudentsCSVHelper.hasCSVFormat(file)) {
            try {
                addStudentSaveResponse = fileService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage<>(message, 200, addStudentSaveResponse));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage<>(message, 203, null));
            }
        }
        message = "Please upload an excel or csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage<>(message, 203, null));
    }
    @GetMapping("/students")
    public ResponseEntity<List<StudentDetails>> getAllStudents() {
        try {
            List<StudentDetails> students = fileService.getStudents();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDetails> getStudentById(@PathVariable UUID id) {
        try{
            Student student = studentDAO.get(id);
            StudentDetails studentDetails = new StudentDetails();
            studentDetails.setStudent_id(student.getStudent_id());
            studentDetails.setEmail(student.getEmail());
            studentDetails.setFirst_name(student.getFirst_name());
            studentDetails.setLast_name(student.getLast_name());
            return new ResponseEntity<>(studentDetails, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/student/find/{email}")
    public ResponseEntity<StudentDetails> getStudentByEmail(@PathVariable String email) {
       try{
           Student student = studentDAO.getByEmail(email);
           StudentDetails studentDetails = new StudentDetails();
           studentDetails.setStudent_id(student.getStudent_id());
           studentDetails.setEmail(student.getEmail());
           studentDetails.setFirst_name(student.getFirst_name());
           studentDetails.setLast_name(student.getLast_name());
           return new ResponseEntity<>(studentDetails, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
