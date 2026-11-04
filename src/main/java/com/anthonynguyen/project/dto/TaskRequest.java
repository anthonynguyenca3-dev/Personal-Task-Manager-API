package com.anthonynguyen.project.dto;

import com.anthonynguyen.project.domain.Priority;

import java.time.LocalDate;

public class TaskRequest {

    private String title;

    private Priority priority;

    private LocalDate dueDate;

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
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

}
