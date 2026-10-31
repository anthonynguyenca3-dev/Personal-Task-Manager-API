package com.anthonynguyen.project.service;

import com.anthonynguyen.project.domain.Priority;
import com.anthonynguyen.project.domain.Task;
import com.anthonynguyen.project.domain.TaskStatus;
import com.anthonynguyen.project.dto.TaskUpdateRequest;
import com.anthonynguyen.project.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(TaskStatus taskStatus, Priority priority, Boolean overdue, String sortBy, String sortDirection) {

        List<Task> tasks;

        if(Boolean.TRUE.equals(overdue)) {

            tasks = taskRepository.findByDueDateBeforeAndTaskStatusNot(LocalDate.now(), TaskStatus.COMPLETED);

            if(taskStatus != null) {

                tasks = tasks.stream().filter(task -> task.getTaskStatus() == taskStatus).collect(Collectors.toCollection(ArrayList::new));

            }

            if (priority != null) {

                tasks = tasks.stream().filter(task -> task.getPriority() == priority).collect(Collectors.toCollection(ArrayList::new));

            }

        } else if (taskStatus != null && priority != null) {

            tasks = taskRepository.findByTaskStatusAndPriority(taskStatus, priority);


        } else if(taskStatus != null) {

            tasks = taskRepository.findByTaskStatus(taskStatus);

        } else if(priority != null){

            tasks = taskRepository.findByPriority(priority);

        } else {

            tasks = taskRepository.findAll();

        }

        if (sortBy != null) {

            if (sortBy.equalsIgnoreCase("priority") && (sortDirection == null || sortDirection.equalsIgnoreCase("ascending"))) {

                tasks.sort(Comparator.comparing(Task::getPriority, Comparator.nullsLast(Comparator.naturalOrder())).thenComparing(Task::getId));

            } else if (sortBy.equalsIgnoreCase("priority") && sortDirection.equalsIgnoreCase("descending")) {

                tasks.sort(Comparator.comparing(Task::getPriority, Comparator.nullsFirst(Comparator.naturalOrder())).reversed().thenComparing(Task::getId));

            }

        }

        return tasks;

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

    public Task editTask(long id, TaskUpdateRequest editField) {

        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {

            Task existingTask = taskOptional.get();

            if(editField.getTitle() != null) {
                existingTask.setTitle(editField.getTitle());
            }

            if(editField.getPriority() != null) {
                existingTask.setPriority(editField.getPriority());
            }

            if(editField.getDueDate() != null) {
                existingTask.setDueDate(editField.getDueDate());
            }

            if(editField.getTaskStatus() != null) {
                existingTask.setTaskStatus(editField.getTaskStatus());
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