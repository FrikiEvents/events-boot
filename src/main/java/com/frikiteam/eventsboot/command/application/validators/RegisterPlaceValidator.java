package com.frikiteam.eventsboot.command.application.validators;

import com.frikiteam.eventsboot.command.application.dtos.RegisterPlaceRequest;
import com.frikiteam.eventsboot.infrastructure.repositories.PlaceRepository;
import org.springframework.stereotype.Component;
import com.frikiteam.eventsboot.command.application.dtos.RegisterEventInformationRequest;
import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.infrastructure.repositories.EventInformationRepository;

@Component
public class RegisterPlaceValidator {
    private final PlaceRepository placeRepository;

    public RegisterPlaceValidator(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Notification validate(RegisterPlaceRequest registerPlaceRequest)
    {
        Notification notification = new Notification();

        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
