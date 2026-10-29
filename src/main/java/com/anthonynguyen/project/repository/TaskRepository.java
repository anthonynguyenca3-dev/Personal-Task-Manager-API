package com.anthonynguyen.project.repository;

import com.anthonynguyen.project.domain.Priority;
import com.anthonynguyen.project.domain.Task;
import com.anthonynguyen.project.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTaskStatus(TaskStatus taskStatus);

    List<Task> findByPriority(Priority priority);

    List<Task> findByTaskStatusAndPriority(TaskStatus taskStatus, Priority priority);

    List<Task> findByDueDateBeforeAndTaskStatusNot(LocalDate today, TaskStatus excludedStatus);

}
