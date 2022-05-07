package com.frikiteam.eventsboot.command.application.handlers;

import com.frikiteam.eventsboot.command.application.commands.RegisterEventInformation;
import com.frikiteam.eventsboot.command.application.commands.RegisterPlace;
import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.command.application.notification.Result;
import com.frikiteam.eventsboot.domain.entities.EventInformation;
import com.frikiteam.eventsboot.domain.entities.Place;
import com.frikiteam.eventsboot.domain.values.EventId;
import com.frikiteam.eventsboot.domain.values.PlaceId;
import com.frikiteam.eventsboot.infrastructure.repositories.EventInformationRepository;
import com.frikiteam.eventsboot.infrastructure.repositories.PlaceRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegisterPlaceHandler {
    private PlaceRepository placeRepository;

    public RegisterPlaceHandler(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @CommandHandler
    public Result<Place, Notification> handle(RegisterPlace registerPlace) {
        Notification notification = new Notification();
        PlaceId placeId = PlaceId.of(UUID.fromString(registerPlace.getId()));
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        Place place = new Place(
                placeId,
                registerPlace.getDistrict(),
                registerPlace.getCountry(),
                registerPlace.getCity(),
                registerPlace.getAuditTrail(),
                registerPlace.getPlaceName());
        placeRepository.save(place);
        return Result.success(place);


    }
}
