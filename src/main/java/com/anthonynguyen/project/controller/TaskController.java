package com.anthonynguyen.project.controller;

import com.anthonynguyen.project.domain.Priority;
import com.anthonynguyen.project.domain.TaskStatus;
import com.anthonynguyen.project.dto.TaskRequest;
import com.anthonynguyen.project.dto.TaskResponse;
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
    public List<TaskResponse> getAllTasks(@RequestParam(required = false) TaskStatus taskStatus,
                                  @RequestParam(required = false) Priority priority,
                                  @RequestParam(required = false) Boolean overdue,
                                  @RequestParam(required = false) String sortBy,
                                  @RequestParam(required = false, defaultValue = "ascending") String sortDirection) {

        return taskService.getAllTasks(taskStatus, priority, overdue, sortBy, sortDirection);

    }

    @GetMapping("/api/tasks/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable long id) {

        TaskResponse task = taskService.getTaskById(id);

        return ResponseEntity.ok(task);

    }

    @PostMapping("/api/tasks")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest addTask) {

        TaskResponse task = taskService.createTask(addTask);

        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PatchMapping("/api/tasks/{id}")
    public ResponseEntity<TaskResponse> editTask(@PathVariable long id, @Valid @RequestBody TaskUpdateRequest editField) {

        TaskResponse task = taskService.editTask(id, editField);

        return ResponseEntity.ok(task);

    }

    @DeleteMapping("/api/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {

            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();

    }
}
