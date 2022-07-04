package com.frikiteam.eventsboot.command.application.dtos;

import lombok.Data;

@Data
public class RequestView {
    private String requestId;
    private String requestDescription;
    private Integer passengerId;
    private Integer employeeId;
    private String createdAt;
    private String updatedAt;
}
