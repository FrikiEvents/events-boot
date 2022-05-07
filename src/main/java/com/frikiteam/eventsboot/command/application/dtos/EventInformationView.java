package com.frikiteam.eventsboot.command.application.dtos;

import lombok.Data;

@Data
public class EventInformationView {
    private String eventInformationId;
    private String eventDescription;
    private String eventImage;
    private String eventLink;
    private String startDate;
    private String endDate;
}