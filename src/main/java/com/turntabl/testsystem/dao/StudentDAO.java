package com.turntabl.testsystem.dao;
import com.turntabl.testsystem.model.Student;
import com.turntabl.testsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentDAO{
//autowiring student repository
    @Autowired
    private StudentRepository studentRepository;
    //constructor - no parameters
    public StudentDAO() {
    }
    //constructor parameters
    public StudentDAO(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student get(UUID id) {
        Student student = new Student();
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            student = optionalStudent.get();
        }
        return student;
    }
    public Optional<Student> findByEmail(String email){
       return studentRepository.findByEmail(email);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student add(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Student student, String[] params) {
       Student student_from_db = studentRepository.findById(student.getStudent_id()).get();
       student_from_db.setFirst_name(student.getFirst_name());
       student_from_db.setLast_name(student.getLast_name());
       student_from_db.setEmail(student.getEmail());
       return studentRepository.save(student_from_db);
    }

    public boolean delete(Student student) {
        boolean isDeleted = false;
        Optional<Student> student_found = studentRepository.findById(student.getStudent_id());
        if(student_found.isPresent()){
             studentRepository.delete(student);
             isDeleted = true;
        }
        return isDeleted;
    }
    public List<Student> addAll(List<Student> t) {
        return studentRepository.saveAll(t);
    }
}
