package com.frikiteam.eventsboot.command.application.dtos;

import com.frikiteam.eventsboot.domain.values.AuditTrail;
import com.frikiteam.eventsboot.domain.values.City;
import com.frikiteam.eventsboot.domain.values.Country;
import com.frikiteam.eventsboot.domain.values.District;
import lombok.Data;
import lombok.Value;

@Data
public class RegisterPlaceRequest {


    private District district;
    private Country country;
    private City city;
    private AuditTrail auditTrail;
    private String placeName;
}