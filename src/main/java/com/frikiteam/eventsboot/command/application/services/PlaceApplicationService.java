package com.frikiteam.eventsboot.command.application.services;

import com.frikiteam.eventsboot.command.application.commands.RegisterPlace;
import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.command.application.notification.Result;

import com.frikiteam.eventsboot.command.application.queries.GetEventInformationByIdQuery;
import com.frikiteam.eventsboot.command.application.queries.GetPlaceByIdQuery;
import com.frikiteam.eventsboot.command.application.validators.RegisterPlaceValidator;

import org.axonframework.commandhandling.gateway.CommandGateway;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import com.frikiteam.eventsboot.command.application.dtos.*;
import com.frikiteam.eventsboot.command.application.enums.ResultType;


import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class PlaceApplicationService {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final RegisterPlaceValidator registerPlaceValidator;


    public PlaceApplicationService(RegisterPlaceValidator registerPlaceValidator,CommandGateway commandGateway, QueryGateway queryGateway) {
        this.registerPlaceValidator = registerPlaceValidator;

        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    public Result<RegisterPlaceResponse, Notification> registerPlace(RegisterPlaceRequest registerPlaceRequest) throws Exception {
        Notification notification = this.registerPlaceValidator.validate(registerPlaceRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String placeId = UUID.randomUUID().toString();
        RegisterPlace registerPlace = new RegisterPlace(
                placeId,
                registerPlaceRequest.getDistrict(),
                registerPlaceRequest.getCountry(),
                registerPlaceRequest.getCity(),
                registerPlaceRequest.getAuditTrail(),
                registerPlaceRequest.getPlaceName());

        CompletableFuture<Object> future = commandGateway.send(registerPlace);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterPlaceResponse registerPlaceResponse = new RegisterPlaceResponse(
                registerPlace.getId(),
                registerPlace.getDistrict(),
                registerPlace.getCountry(),
                registerPlace.getCity(),
                registerPlace.getAuditTrail(),
                registerPlace.getPlaceName());

        return Result.success(registerPlaceResponse);
    }
    public PlaceView getById(String placeId) throws Exception {
        CompletableFuture<PlaceView> future = queryGateway.query(new GetPlaceByIdQuery(placeId), ResponseTypes.instanceOf(PlaceView.class));
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        return future.get();
    }
}