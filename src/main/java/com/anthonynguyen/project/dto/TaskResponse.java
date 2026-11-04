package com.anthonynguyen.project.dto;

import com.anthonynguyen.project.domain.Priority;
import com.anthonynguyen.project.domain.TaskStatus;

import java.time.LocalDate;

public class TaskResponse {

    private final Long id;

    private final String title;

    private final Priority priority;

    private final LocalDate dueDate;

    private final TaskStatus taskStatus;

    public TaskResponse(Long id, String title, Priority priority, LocalDate dueDate, TaskStatus taskStatus) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
        this.taskStatus = taskStatus;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

}
