package com.projects.InvoiceManagementSystem.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class InvoiceHistory {
    @Id
    private UUID invoiceId;
    private String event;
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserDetail createdBy;
}
