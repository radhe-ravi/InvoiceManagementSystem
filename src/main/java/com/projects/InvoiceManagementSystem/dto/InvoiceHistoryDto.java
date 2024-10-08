package com.projects.InvoiceManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
public class InvoiceHistoryDto {
    private UUID invoiceHistoryId;
    private UUID invoiceId;
    private String event;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy:MM:dd hh:mm:ss")
    private LocalDateTime createdOn;
    private UUID createdBy;

}
