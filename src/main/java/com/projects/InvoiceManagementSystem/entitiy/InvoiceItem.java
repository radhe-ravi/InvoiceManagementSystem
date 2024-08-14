package com.projects.InvoiceManagementSystem.entitiy;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID invoiceItemId;


    private String itemName;
    private String itemDescription;
    private LocalDateTime createdOn;

    @ManyToOne
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "created_by")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private UserDetail createdBy;
}
