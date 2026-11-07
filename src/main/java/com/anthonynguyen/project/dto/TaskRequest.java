package com.anthonynguyen.project.dto;

import com.anthonynguyen.project.domain.Priority;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TaskRequest {

    @NotBlank(message = "Title must be given.")
    @Size(max = 100, message = "Title must be at most 100 characters.")
    private String title;

    private Priority priority;

    @FutureOrPresent(message = "Cannot insert a date in the past")
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
