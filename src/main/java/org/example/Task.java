package org.example;


import java.time.LocalDate;

public class Task {
    String taskName;
    int urgency;
    Course course;
    LocalDate givenDate;
    LocalDate dueDate;


    public Task(String taskName,int urgency, Course course,LocalDate givenDate, LocalDate dueDate) {
        this.taskName = taskName;
        this.urgency = urgency;
        this.course = course;
        this.givenDate = givenDate;
        this.dueDate = dueDate;


    }

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

