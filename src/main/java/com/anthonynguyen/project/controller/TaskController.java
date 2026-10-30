package com.anthonynguyen.project.controller;

import com.anthonynguyen.project.domain.Priority;
import com.anthonynguyen.project.domain.Task;
import com.anthonynguyen.project.domain.TaskStatus;
import com.anthonynguyen.project.dto.TaskUpdateRequest;
import com.anthonynguyen.project.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/api/tasks")
    public List<Task> getAllTasks(@RequestParam(required = false) TaskStatus taskStatus,
                                  @RequestParam(required = false) Priority priority,
                                  @RequestParam(required = false) Boolean overdue,
                                  @RequestParam(required = false) String sortBy,
                                  @RequestParam(required = false, defaultValue = "ascending") String sortDirection) {

        return taskService.getAllTasks(taskStatus, priority, overdue, sortBy, sortDirection);

    }

    @GetMapping("/api/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) {

        Task task = taskService.getTaskById(id);

        if (task == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(task);
        }

    }

    @PostMapping("/api/tasks")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task addTask) {

        Task task = taskService.createTask(addTask.getTitle(), addTask.getPriority(), addTask.getDueDate());

        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PatchMapping("/api/tasks/{id}")
    public ResponseEntity<Task> editTask(@PathVariable long id, @RequestBody TaskUpdateRequest editField) {

        Task task = taskService.editTask(id, editField);


        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/api/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {

        Task task = taskService.getTaskById(id);

        if (task == null) {
            return ResponseEntity.notFound().build();
        } else {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        }

    }

}
