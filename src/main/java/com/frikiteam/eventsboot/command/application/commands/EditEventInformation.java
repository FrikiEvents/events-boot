package com.frikiteam.eventsboot.command.application.commands;

import lombok.Value;

@Value
public class EditEventInformation {
    private String id;
    private String eventDescription;
    private String eventImage;
    private String eventLink;
    private String startDate;
    private String endDate;
}
