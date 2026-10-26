package com.anthonynguyen.project.service;

import com.anthonynguyen.project.domain.Priority;
import com.anthonynguyen.project.domain.Task;
import com.anthonynguyen.project.domain.TaskStatus;
import com.anthonynguyen.project.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Task getTaskById(long id) {

        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            return taskOptional.get();
        } else {
            return null;
        }
    }

    public Task createTask(String title, Priority priority, LocalDate dueDate) {

        Task task = new Task(title, priority, dueDate);

        return taskRepository.save(task);

    }

    public Task editTask(long id, String title, Priority priority, LocalDate dueDate, TaskStatus taskStatus) {

        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {

            Task existingTask = taskOptional.get();

            if(title != null) {
                existingTask.setTitle(title);
            }

            if(priority != null) {
                existingTask.setPriority(priority);
            }

            if(dueDate != null) {
                existingTask.setDueDate(dueDate);
            }

            if(taskStatus != null) {
                existingTask.setTaskStatus(taskStatus);
            }

            return taskRepository.save(existingTask);

        } else {
            return null;
        }

    }

    public void deleteTask(long id){

        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {

           taskRepository.deleteById(id);

        }

    }
}