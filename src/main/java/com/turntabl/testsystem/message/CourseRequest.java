package com.turntabl.testsystem.message;

public class CourseRequest {
    private String courseName;
    public CourseRequest() {
    }
    public CourseRequest(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
