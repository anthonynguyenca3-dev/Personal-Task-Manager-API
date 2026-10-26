package com.anthonynguyen.project.repository;

import com.anthonynguyen.project.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {



}
