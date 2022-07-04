package com.frikiteam.eventsboot.command.application.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Value
public class RegisterRequest {
    @TargetAggregateIdentifier
    private String id;
    private String requestDescription;
    private Integer passengerId;
    private Integer employeeId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}