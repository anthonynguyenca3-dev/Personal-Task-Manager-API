package com.anthonynguyen.project.repository;

import com.anthonynguyen.project.domain.Priority;
import com.anthonynguyen.project.domain.Task;
import com.anthonynguyen.project.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser_Id(Long userId);

    List<Task> findByUser_IdAndTaskStatus(Long userId, TaskStatus taskStatus);

    List<Task> findByUser_IdAndPriority(Long userId, Priority priority);

    List<Task> findByUser_IdAndTaskStatusAndPriority(Long userId, TaskStatus taskStatus, Priority priority);

    List<Task> findByUser_IdAndDueDateBeforeAndTaskStatusNot(Long userId, LocalDate today, TaskStatus excludedStatus);

    Optional<Task> findByIdAndUser_Id(Long id, Long userId);

}
