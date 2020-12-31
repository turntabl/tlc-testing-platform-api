package com.turntabl.testsystem.message;

public class CourseRequest {
    private long course_id;
    private String courseName;
    private String user_id;
    public CourseRequest() {
    }

    public CourseRequest(long course_id, String courseName, String user_id) {
        this.course_id = course_id;
        this.courseName = courseName;
        this.user_id = user_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
