package com.anthonynguyen.project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Title must be given.")
    @Size(max = 100, message = "Title must be at most 100 characters.")
    private String title;

    @Enumerated(EnumType.STRING)
    private Priority priority;
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus = TaskStatus.TO_DO;

    public Task(String title, Priority priority, LocalDate dueDate) {

        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;

    }

    protected Task() {}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setTaskStatus(TaskStatus taskStatus) {this.taskStatus = taskStatus;}

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public TaskStatus getTaskStatus() { return taskStatus; }

    public Long getId() {return id; }

}

