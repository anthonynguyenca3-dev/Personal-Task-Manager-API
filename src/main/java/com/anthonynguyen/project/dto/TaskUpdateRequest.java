package com.anthonynguyen.project.dto;

import com.anthonynguyen.project.domain.Priority;
import com.anthonynguyen.project.domain.TaskStatus;

import java.time.LocalDate;

public class TaskUpdateRequest {

    private String title;

    private Priority priority;

    private LocalDate dueDate;

    private TaskStatus taskStatus;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

}
