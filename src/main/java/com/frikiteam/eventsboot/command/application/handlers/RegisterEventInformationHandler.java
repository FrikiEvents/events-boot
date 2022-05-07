package com.frikiteam.eventsboot.command.application.handlers;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;
import com.frikiteam.eventsboot.infrastructure.repositories.EventInformationRepository;
import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.command.application.notification.Result;
import com.frikiteam.eventsboot.domain.entities.EventInformation;
import com.frikiteam.eventsboot.command.application.commands.RegisterEventInformation;
import com.frikiteam.eventsboot.domain.values.*;
import java.util.UUID;

@Component
public class RegisterEventInformationHandler {
    private EventInformationRepository personRepository;

    public RegisterEventInformationHandler(EventInformationRepository personRepository) {
        this.personRepository = personRepository;
    }

    @CommandHandler
    public Result<EventInformation, Notification> handle(RegisterEventInformation registerPerson) {
        Notification notification = new Notification();
        EventId clientId = EventId.of(UUID.fromString(registerPerson.getId()));
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        EventInformation person = new EventInformation(
                clientId,
                registerPerson.getEventDescription(),
                registerPerson.getEventImage(),
                registerPerson.getEventLink(),
                registerPerson.getStartDate(),
                registerPerson.getEndDate());
        personRepository.save(person);
        return Result.success(person);
    }

}
