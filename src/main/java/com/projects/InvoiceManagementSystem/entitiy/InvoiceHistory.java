package com.projects.InvoiceManagementSystem.entitiy;


import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class InvoiceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID invoiceId;
    private String event;
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserDetail createdBy;
}
