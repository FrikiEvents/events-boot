package com.frikiteam.eventsboot.command.application.dtos;

import lombok.Value;

import java.time.LocalDateTime;


@Value
public class RegisterEventInformationRequest {
    private String eventDescription;
    private String eventImage;
    private String eventLink;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}