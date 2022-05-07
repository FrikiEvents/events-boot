package com.frikiteam.eventsboot.command.application.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Value
public class RegisterEventInformation {
    @TargetAggregateIdentifier
    private String id;
    private String eventDescription;
    private String eventImage;
    private String eventLink;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
