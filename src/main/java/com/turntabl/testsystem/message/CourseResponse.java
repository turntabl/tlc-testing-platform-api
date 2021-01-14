package com.turntabl.testsystem.message;

public class CourseResponse {
    private String message;
    private long courseId;
    private String courseName;
    public CourseResponse() {
    }

    public CourseResponse(String message, long courseId, String courseName) {
        this.message = message;
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
