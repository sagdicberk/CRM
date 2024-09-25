package com.sgdcbrk.crm.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime date;
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
