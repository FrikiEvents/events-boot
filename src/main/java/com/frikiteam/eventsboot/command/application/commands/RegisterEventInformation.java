package com.frikiteam.eventsboot.command.application.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class RegisterEventInformation {
    @TargetAggregateIdentifier
    private String id;
    private String eventDescription;
    private String eventImage;
    private String eventLink;
    private String startDate;
    private String endDate;
}
