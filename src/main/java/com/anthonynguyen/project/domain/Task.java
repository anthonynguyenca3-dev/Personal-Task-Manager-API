package com.anthonynguyen.project.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String title;

    @Enumerated(EnumType.STRING)
    private Priority priority;
    private LocalDate dueDate;

    protected Task() {}

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus = TaskStatus.TO_DO;

}

