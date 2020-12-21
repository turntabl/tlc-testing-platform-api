package com.turntabl.testsystem.message;

public class CourseRequest {
    private long course_id;
    private String courseName;
    public CourseRequest() {
    }

    public CourseRequest(long course_id, String courseName) {
        this.course_id = course_id;
        this.courseName = courseName;
    }

    public long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
