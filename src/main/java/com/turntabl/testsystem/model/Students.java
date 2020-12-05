package com.turntabl.testsystem.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Students {

    private List<Student> studentList;

    public Students() {
        this.studentList = new ArrayList<>();
    }


    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public Student getStudentById(UUID id){
        Student studentR = new Student();
        for (Student student:this.studentList) {
            if(student.getStudent_id().compareTo(id) == 0){
                studentR =  student;
            }
        }

        return studentR;
    }
}
