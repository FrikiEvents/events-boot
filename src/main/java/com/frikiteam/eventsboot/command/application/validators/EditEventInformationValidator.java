package com.frikiteam.eventsboot.command.application.validators;

import org.springframework.stereotype.Component;
import com.frikiteam.eventsboot.command.application.dtos.EditEventInformationRequest;
import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.domain.entities.EventInformation;
import com.frikiteam.eventsboot.infrastructure.repositories.EventInformationRepository;

import java.util.Optional;
import java.util.UUID;

@Component
public class EditEventInformationValidator {
    private final EventInformationRepository personRepository;

    public EditEventInformationValidator(EventInformationRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Notification validate(EditEventInformationRequest editEventInformationRequest)
    {
        Notification notification = new Notification();
        String id = editEventInformationRequest.getId().trim();
        if (id.isEmpty()) {
            notification.addError("EventInformation id is required");
        }
        Optional<EventInformation> personOptional = personRepository.findById(UUID.fromString(id));
        if (personOptional.isPresent()) {
            notification.addError("EventInformation not found");
            return notification;
        }
        String eventDescription = editEventInformationRequest.getEventDescription().trim();
        if (eventDescription.isEmpty()) {
            notification.addError("EventInformation eventDescription is required");
        }
        String eventImage = editEventInformationRequest.getEventImage().trim();
        if (eventImage.isEmpty()) {
            notification.addError("EventInformation eventImage is required");
        }
        String eventLink = editEventInformationRequest.getEventLink().trim();
        if (eventLink.isEmpty()) {
            notification.addError("EventInformation eventLink is required");
        }
        String startDate = editEventInformationRequest.getStartDate().trim();
        if (startDate.isEmpty()) {
            notification.addError("EventInformation startDate is required");
        }
        String endDate = editEventInformationRequest.getEndDate().trim();
        if (endDate.isEmpty()) {
            notification.addError("EventInformation endDate is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
