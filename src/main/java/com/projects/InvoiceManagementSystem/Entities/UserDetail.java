package com.projects.InvoiceManagementSystem.Entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class UserDetail {

    @Id
    private UUID userId;

    private String userName;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy:MM:dd hh:mm:ss")
    private LocalDateTime createdOn;

    private String status;
}
