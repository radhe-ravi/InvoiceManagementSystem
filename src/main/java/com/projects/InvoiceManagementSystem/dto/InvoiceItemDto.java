package com.projects.InvoiceManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class InvoiceItemDto {
    private UUID invoiceItemId;
    private UUID invoiceId;
    private String itemName;
    private String itemDescription;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy:MM:dd hh:mm:ss")
    private LocalDateTime createdOn;
    private UUID createdBy;
}
