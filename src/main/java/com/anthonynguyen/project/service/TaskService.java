package com.anthonynguyen.project.service;

import com.anthonynguyen.project.domain.Task;
import com.anthonynguyen.project.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {

        Optional<Task> taskOptional = taskRepository.findById(id);

        if(taskOptional.isPresent()){
            return taskOptional.get();
        } else {
            return null;
        }
    }
}
