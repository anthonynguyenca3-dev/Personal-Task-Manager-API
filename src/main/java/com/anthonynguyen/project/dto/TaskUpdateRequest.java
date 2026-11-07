package com.anthonynguyen.project.dto;

import com.anthonynguyen.project.domain.Priority;
import com.anthonynguyen.project.domain.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TaskUpdateRequest {

    @Pattern(regexp = ".*\\S.*")
    @Size(max = 100, message = "Title must be at most 100 characters.")
    private String title;

    private Priority priority;

    @FutureOrPresent(message = "Cannot insert a date in the past")
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
