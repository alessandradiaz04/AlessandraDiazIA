package org.example;


import java.time.LocalDate;

public class Task {  //All controllers can access the class / object
    String taskName;  //Initialize variables and common attributes
    int urgency;
    Course course;
    LocalDate givenDate;
    LocalDate dueDate;


    public Task(String taskName,int urgency, Course course,LocalDate givenDate, LocalDate dueDate) { //All the requirements needed to create a Task
        this.taskName = taskName;
        this.urgency = urgency;
        this.course = course;
        this.givenDate = givenDate;
        this.dueDate = dueDate;


    }
 //GET-SET--> saves code, allows for the option  to modify  if behavior/content changes
    public String getTaskName() {
        return taskName;
    }
    public int getUrgency() {
        return urgency;
    }
    public Course getCourse() { return course;
    }
    public LocalDate getGivenDate() {
        return givenDate;
    }
    public LocalDate getDueDate() { return dueDate; }

@Override
    public String toString() {
        return taskName;
    }

}

