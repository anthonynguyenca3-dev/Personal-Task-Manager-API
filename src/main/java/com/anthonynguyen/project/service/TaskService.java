package com.anthonynguyen.project.service;

import com.anthonynguyen.project.domain.Priority;
import com.anthonynguyen.project.domain.Task;
import com.anthonynguyen.project.domain.TaskStatus;
import com.anthonynguyen.project.domain.User;
import com.anthonynguyen.project.dto.TaskRequest;
import com.anthonynguyen.project.dto.TaskResponse;
import com.anthonynguyen.project.dto.TaskUpdateRequest;
import com.anthonynguyen.project.exception.AuthenticatedUserNotFoundException;
import com.anthonynguyen.project.exception.TaskNotFoundException;
import com.anthonynguyen.project.repository.TaskRepository;
import com.anthonynguyen.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskResponse> getAllTasks(TaskStatus taskStatus, Priority priority, Boolean overdue, String sortBy, String sortDirection) {

        Long userId = getAuthenticatedUserId();

        List<Task> tasks;

        if(Boolean.TRUE.equals(overdue)) {

            tasks = taskRepository.findByUser_IdAndDueDateBeforeAndTaskStatusNot(userId, LocalDate.now(), TaskStatus.COMPLETED);

            if(taskStatus != null) {

                tasks = tasks.stream().filter(task -> task.getTaskStatus() == taskStatus).collect(Collectors.toCollection(ArrayList::new));

            }

            if (priority != null) {

                tasks = tasks.stream().filter(task -> task.getPriority() == priority).collect(Collectors.toCollection(ArrayList::new));

            }

        } else if (taskStatus != null && priority != null) {

            tasks = taskRepository.findByUser_IdAndTaskStatusAndPriority(userId, taskStatus, priority);


        } else if(taskStatus != null) {

            tasks = taskRepository.findByUser_IdAndTaskStatus(userId, taskStatus);

        } else if(priority != null){

            tasks = taskRepository.findByUser_IdAndPriority(userId, priority);

        } else {

            tasks = taskRepository.findByUser_Id(userId);

        }

        if (sortBy != null) {

            if (sortBy.equalsIgnoreCase("priority") && (sortDirection == null || sortDirection.equalsIgnoreCase("ascending"))) {

                tasks.sort(Comparator.comparing(Task::getPriority, Comparator.nullsLast(Comparator.naturalOrder())).thenComparing(Task::getId));

            } else if (sortBy.equalsIgnoreCase("priority") && sortDirection.equalsIgnoreCase("descending")) {

                tasks.sort(Comparator.comparing(Task::getPriority, Comparator.nullsFirst(Comparator.naturalOrder())).reversed().thenComparing(Task::getId));

            }

        }

        List<TaskResponse> getTasks = tasks.stream().map(task -> new TaskResponse(task.getId(), task.getTitle(), task.getPriority(), task.getDueDate(), task.getTaskStatus())).toList();

        return getTasks;

    }

    public TaskResponse getTaskById(long id) {

        Long userId = getAuthenticatedUserId();

        Optional<Task> taskOptional = taskRepository.findByIdAndUser_Id(id, userId);

        if (taskOptional.isPresent()) {
            return new TaskResponse(taskOptional.get().getId(), taskOptional.get().getTitle(), taskOptional.get().getPriority(), taskOptional.get().getDueDate(), taskOptional.get().getTaskStatus());
        } else {
            throw new TaskNotFoundException("Task #" + id + " was not found");
        }
    }

    public TaskResponse createTask(TaskRequest addTask) {

        Long userId = getAuthenticatedUserId();
        Optional<User> userOptional = userRepository.findById(userId);
        User user;

        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new AuthenticatedUserNotFoundException("Authenticated user account could not be found");
        }

        Task task = new Task(addTask.getTitle(), addTask.getPriority(), addTask.getDueDate());

        task.setUser(user);

        Task savedTask = taskRepository.save(task);

        logger.info("Task #{} created successfully", savedTask.getId());

        return new TaskResponse(savedTask.getId(), savedTask.getTitle(), savedTask.getPriority(), savedTask.getDueDate(), savedTask.getTaskStatus());
    }

    public TaskResponse editTask(long id, TaskUpdateRequest editField) {

        Long userId = getAuthenticatedUserId();

        Optional<Task> taskOptional = taskRepository.findByIdAndUser_Id(id, userId);

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

            Task savedTask = taskRepository.save(existingTask);

            logger.info("Task #{} was updated successfully", savedTask.getId());

            return new TaskResponse(savedTask.getId(), savedTask.getTitle(), savedTask.getPriority(), savedTask.getDueDate(), savedTask.getTaskStatus());

        } else {
            throw new TaskNotFoundException("Task #" + id + " was not found");
        }

    }

    public void deleteTask(long id){

        Long userId = getAuthenticatedUserId();

        Optional<Task> taskOptional = taskRepository.findByIdAndUser_Id(id, userId);

        if (taskOptional.isPresent()) {

            Task existingTask = taskOptional.get();
            taskRepository.delete(existingTask);

            logger.info("Task #{} was deleted successfully", id);

        } else {
            throw new TaskNotFoundException("Task #" + id + " was not found");
        }

    }

    private Long getAuthenticatedUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();

        Long userId = Long.valueOf((String)principal);

        return userId;

    }
}