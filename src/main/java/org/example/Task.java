package org.example;


import java.time.LocalDate;

public class Task {
    String taskName;
    String course;
    LocalDate givenDate;
    LocalDate dueDate;
    int urgency;

    public Task(String taskName,String course,LocalDate givenDate, LocalDate dueDate, int urgency) {
        this.taskName = taskName;
        this.course = course;
        this.givenDate = givenDate;
        this.dueDate = dueDate;
        this.urgency = urgency;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }


    public String getTaskName() {
        return taskName;
    }
    public String getCourse() {
        return course;
    }

    public LocalDate getGivenDate() {
        return givenDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getUrgency() {
        return urgency;
    }

}

