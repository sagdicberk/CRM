package com.sgdcbrk.crm.model.task;

import com.sgdcbrk.crm.model.customer.Customer;
import com.sgdcbrk.crm.model.opportunity.Opportunity;
import com.sgdcbrk.crm.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Opportunity.class)
    @JoinColumn(name = "opportunity_id")
    private Opportunity opportunity;

    @Enumerated(EnumType.STRING)
    private MeetingType type;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDate dateOfMeeting;
}
