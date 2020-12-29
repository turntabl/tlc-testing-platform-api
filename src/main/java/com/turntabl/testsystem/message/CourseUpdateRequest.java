package com.turntabl.testsystem.message;

public class CourseUpdateRequest {
    private long courseId;
    private String courseName;

    public CourseUpdateRequest() {
    }

    public CourseUpdateRequest(long courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
