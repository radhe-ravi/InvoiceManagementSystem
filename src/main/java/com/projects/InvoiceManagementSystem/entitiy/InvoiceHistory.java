package com.projects.InvoiceManagementSystem.entitiy;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class InvoiceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "history_id", nullable = false)
    private UUID invoiceId;

    private String event;
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "created_by")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private UserDetail createdBy;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

}


