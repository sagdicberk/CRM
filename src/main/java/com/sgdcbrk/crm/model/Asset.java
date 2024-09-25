package com.sgdcbrk.crm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type; // Finansal, Gayrimenkul vb.
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
