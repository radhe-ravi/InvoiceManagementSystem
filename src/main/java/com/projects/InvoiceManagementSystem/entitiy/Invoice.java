package com.projects.InvoiceManagementSystem.entitiy;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID invoiceId;
    private String invoiceNo;
    private String panNo;
    private String gstNo;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy:MM:dd hh:mm:ss")
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "created_by")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private UserDetail createdBy;



    public Invoice(UUID invoiceId) {
        this.invoiceId = invoiceId;
    }
}
