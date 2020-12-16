package com.turntabl.testsystem.message;

public class CourseRequest {
    private String courseName;
    private long courseId;

    public CourseRequest() {
    }

    public CourseRequest(String courseName, long courseId) {
        this.courseName = courseName;
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
