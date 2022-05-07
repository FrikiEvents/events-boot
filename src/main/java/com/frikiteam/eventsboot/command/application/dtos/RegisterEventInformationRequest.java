package com.frikiteam.eventsboot.command.application.dtos;

import lombok.Value;


@Value
public class RegisterEventInformationRequest {
    private String eventDescription;
    private String eventImage;
    private String eventLink;
    private String startDate;
    private String endDate;
}