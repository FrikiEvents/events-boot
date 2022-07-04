package com.frikiteam.eventsboot.command.application.services;

import com.frikiteam.eventsboot.command.application.commands.RegisterRequest;
import com.frikiteam.eventsboot.command.application.dtos.*;
import com.frikiteam.eventsboot.command.application.enums.ResultType;
import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.command.application.notification.Result;
import com.frikiteam.eventsboot.command.application.queries.GetRequestByIdQuery;
import com.frikiteam.eventsboot.command.application.validators.RegisterRequestValidator;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class RequestApplicationService {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final RegisterRequestValidator registerRequestValidator;

    public RequestApplicationService(RegisterRequestValidator registerRequestValidator, CommandGateway commandGateway, QueryGateway queryGateway) {
        this.registerRequestValidator = registerRequestValidator;
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    public Result<RegisterRequestResponse, Notification> registerRequest(RegisterRequestRequest registerRequestRequest) throws Exception {
        Notification notification = this.registerRequestValidator.validate(registerRequestRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String requestId = UUID.randomUUID().toString();
        RegisterRequest registerRequest = new RegisterRequest(
                requestId,
                registerRequestRequest.getRequestDescription().trim(),
                registerRequestRequest.getPassengerId(),
                registerRequestRequest.getEmployeeId(),
                registerRequestRequest.getCreatedAt(),
                registerRequestRequest.getUpdatedAt()
        );
        CompletableFuture<Object> future = commandGateway.send(registerRequest);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterRequestResponse registerRequestResponse = new RegisterRequestResponse(
                registerRequest.getId(),
                registerRequest.getRequestDescription(),
                registerRequest.getPassengerId(),
                registerRequest.getEmployeeId(),
                registerRequest.getCreatedAt(),
                registerRequest.getUpdatedAt()
        );
        return Result.success(registerRequestResponse);
    }

    public RequestView getById(String requestId) throws Exception {
        CompletableFuture<RequestView> future = queryGateway.query(new GetRequestByIdQuery(requestId), ResponseTypes.instanceOf(RequestView.class));
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        return future.get();
    }
}
