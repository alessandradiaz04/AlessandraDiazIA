package org.example;

import java.time.LocalDate;

public class Course {

    String courseName;
    String teacherName;



    public Course(String courseName, String teacherName) { //Assigning attributes to each course
        this.courseName = courseName;
        this.teacherName = teacherName;
}

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }
    // The ChoiceBox uses the toString() method of the object to display options in the dropdown box.
    @Override  // There is a need to override this method to return something more helpful.
    public String toString(){
        return courseName;
    }
 }