package com.frikiteam.eventsboot.command.application.dtos;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RegisterRequestRequest {
    private String requestDescription;
    private Integer employeeId;
    private Integer passengerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
