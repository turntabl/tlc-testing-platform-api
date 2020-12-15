package com.turntabl.testsystem.message;
import com.turntabl.testsystem.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
public class AddStudentSaveResponse {
    private AtomicInteger atomicInteger;
    private List<StudentDetails> studentList = new ArrayList<>();
    public AddStudentSaveResponse() {
    }
    public AddStudentSaveResponse(AtomicInteger atomicInteger, List<StudentDetails> studentList) {
        this.atomicInteger = atomicInteger;
        this.studentList = studentList;
    }
    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }
    public void setAtomicInteger(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }
    public List<StudentDetails> getStudentList() {
        return studentList;
    }
    public void setStudentList(List<StudentDetails> studentList) {
        this.studentList = studentList;
    }
}
