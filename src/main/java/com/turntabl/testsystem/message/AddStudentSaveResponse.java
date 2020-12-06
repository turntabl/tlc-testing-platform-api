package com.turntabl.testsystem.message;

import com.turntabl.testsystem.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AddStudentSaveResponse {

    private AtomicInteger atomicInteger;
    private List<Student> studentList = new ArrayList<>();

    public AddStudentSaveResponse() {
    }

    public AddStudentSaveResponse(AtomicInteger atomicInteger, List<Student> studentList) {
        this.atomicInteger = atomicInteger;
        this.studentList = studentList;
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public void setAtomicInteger(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
