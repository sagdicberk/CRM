package com.sgdcbrk.crm.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
