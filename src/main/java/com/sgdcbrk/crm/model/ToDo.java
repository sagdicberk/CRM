package com.sgdcbrk.crm.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String task;
    private boolean isCompleted;
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
