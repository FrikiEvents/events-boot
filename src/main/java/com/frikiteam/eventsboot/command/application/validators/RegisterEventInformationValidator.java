package com.frikiteam.eventsboot.command.application.validators;

import org.springframework.stereotype.Component;
import com.frikiteam.eventsboot.command.application.dtos.RegisterEventInformationRequest;
import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.infrastructure.repositories.EventInformationRepository;

@Component
public class RegisterEventInformationValidator {
    private final EventInformationRepository personRepository;

    public RegisterEventInformationValidator(EventInformationRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Notification validate(RegisterEventInformationRequest registerEventInformationRequest)
    {
        Notification notification = new Notification();
        String eventDescription = registerEventInformationRequest.getEventDescription() != null ? registerEventInformationRequest.getEventDescription().trim() : "";
        if (eventDescription.isEmpty()) {
            notification.addError("Client eventDescription is required");
        }
        String eventImage = registerEventInformationRequest.getEventImage() != null ? registerEventInformationRequest.getEventImage().trim() : "";
        if (eventImage.isEmpty()) {
            notification.addError("Client firstname is required");
        }
        String eventLink = registerEventInformationRequest.getEventLink() != null ? registerEventInformationRequest.getEventLink().trim() : "";
        if (eventLink.isEmpty()) {
            notification.addError("Client lastname is required");
        }
        String startDate = registerEventInformationRequest.getStartDate() != null ? registerEventInformationRequest.getStartDate().trim() : "";
        if (startDate.isEmpty()) {
            notification.addError("Client startDate is required");
        }
        String endDate = registerEventInformationRequest.getEndDate() != null ? registerEventInformationRequest.getEndDate().trim() : "";
        if (endDate.isEmpty()) {
            notification.addError("Client endDate is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
