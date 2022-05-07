package com.frikiteam.eventsboot.command.application.services;

import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.command.application.notification.Result;
import com.frikiteam.eventsboot.command.application.queries.GetEventInformationByIdQuery;
import com.frikiteam.eventsboot.command.application.validators.RegisterEventInformationValidator;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;
import com.frikiteam.eventsboot.command.application.commands.RegisterEventInformation;
import com.frikiteam.eventsboot.command.application.dtos.*;
import com.frikiteam.eventsboot.command.application.enums.ResultType;
import com.frikiteam.eventsboot.command.application.validators.EditEventInformationValidator;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class EventInformationApplicationService {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final RegisterEventInformationValidator registerEventInformationValidator;
    private final EditEventInformationValidator editEventInformationValidator;

    public EventInformationApplicationService(RegisterEventInformationValidator registerEventInformationValidator, EditEventInformationValidator editEventInformationValidator, CommandGateway commandGateway, QueryGateway queryGateway) {
        this.registerEventInformationValidator = registerEventInformationValidator;
        this.editEventInformationValidator = editEventInformationValidator;
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    public Result<RegisterEventInformationResponse, Notification> registerEventInformation(RegisterEventInformationRequest registerEventInformationRequest) throws Exception {
        Notification notification = this.registerEventInformationValidator.validate(registerEventInformationRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String eventInformationId = UUID.randomUUID().toString();
        RegisterEventInformation registerEventInformation = new RegisterEventInformation(
            eventInformationId,
            registerEventInformationRequest.getEventDescription().trim(),
            registerEventInformationRequest.getEventImage().trim(),
            registerEventInformationRequest.getEventLink().trim(),
            registerEventInformationRequest.getStartDate().trim(),
            registerEventInformationRequest.getEndDate().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(registerEventInformation);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterEventInformationResponse registerEventInformationResponse = new RegisterEventInformationResponse(
            registerEventInformation.getId(),
            registerEventInformation.getEventDescription(),
            registerEventInformation.getEventImage(),
            registerEventInformation.getEventLink(),
            registerEventInformation.getStartDate(),
            registerEventInformation.getEndDate()
        );
        return Result.success(registerEventInformationResponse);
    }

    public EventInformationView getById(String eventInformationId) throws Exception {
        CompletableFuture<EventInformationView> future = queryGateway.query(new GetEventInformationByIdQuery(eventInformationId), ResponseTypes.instanceOf(EventInformationView.class));
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        return future.get();
    }
}