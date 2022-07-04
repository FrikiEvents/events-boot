package com.frikiteam.eventsboot.command.application.handlers;

import com.frikiteam.eventsboot.command.application.commands.RegisterRequest;
import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.command.application.notification.Result;
import com.frikiteam.eventsboot.domain.entities.Request;
import com.frikiteam.eventsboot.domain.values.RequestId;
import com.frikiteam.eventsboot.infrastructure.repositories.RequestRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegisterRequestHandler {
    private RequestRepository requestRepository;

    public RegisterRequestHandler(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @CommandHandler
    public Result<Request, Notification> handle(RegisterRequest registerRequest) {
        Notification notification = new Notification();
        RequestId requestId = RequestId.of(UUID.fromString(registerRequest.getId()));
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        Request request = new Request(
                requestId,
                registerRequest.getRequestDescription(),
                registerRequest.getEmployeeId(),
                registerRequest.getPassengerId(),
                registerRequest.getCreatedAt(),
                registerRequest.getUpdatedAt());
        requestRepository.save(request);
        return Result.success(request);
    }
}
