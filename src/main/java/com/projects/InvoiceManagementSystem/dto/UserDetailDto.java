package com.projects.InvoiceManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto {
    private UUID userId;
    private String userName;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy:MM:dd hh:mm:ss")
    private LocalDateTime createdOn;
    private String status;
}
