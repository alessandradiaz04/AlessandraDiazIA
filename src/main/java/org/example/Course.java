package org.example;

import java.time.LocalDate;

public class Course {

    String courseName;
    String teacherName;



    public Course(String courseName, String teacherName) {
        this.courseName = courseName;
        this.teacherName = teacherName;

}
    // The ChoiceBox uses the toString() method of our object to display options in the dropdown.
    // We need to override this method to return something more helpful.
    public String getCourseName() {
        return courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    }
