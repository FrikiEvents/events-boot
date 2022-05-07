package com.frikiteam.eventsboot.command.application.commands;

import com.frikiteam.eventsboot.domain.values.*;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Value
public class RegisterPlace {
    @TargetAggregateIdentifier
    private String id;
    private District district;
    private Country country;
    private City city;
    private AuditTrail auditTrail;
    private String placeName;
}
