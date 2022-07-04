package com.frikiteam.eventsboot.command.application.dtos;

import lombok.Value;

import java.time.LocalDateTime;
@Value
public class RegisterRequestResponse {
    private String id;
    private String requestDescription;
    private Integer passengerId;
    private Integer employeeId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
