package com.frikiteam.eventsboot.command.application.validators;

import com.frikiteam.eventsboot.command.application.dtos.RegisterRequestRequest;
import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.infrastructure.repositories.RequestRepository;
import org.springframework.stereotype.Component;

@Component
public class RegisterRequestValidator {
    private final RequestRepository requestRepository;

    public RegisterRequestValidator(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Notification validate(RegisterRequestRequest registerRequestRequest)
    {
        Notification notification = new Notification();
        String requestDescription = registerRequestRequest.getRequestDescription() != null ? registerRequestRequest.getRequestDescription().trim() : "";
        if (requestDescription.isEmpty()) {
            notification.addError("Request requestDescription is required");
        }
        int employeeId = registerRequestRequest.getEmployeeId() != null ? registerRequestRequest.getEmployeeId() : 0;
        if (Integer.toString(employeeId).isEmpty()) {
            notification.addError("Request employeeId is required");
        }
        int passengerId = registerRequestRequest.getPassengerId() != null ? registerRequestRequest.getPassengerId(): 0;
        if (Integer.toString(passengerId).isEmpty()) {
            notification.addError("Request passengerId is required");
        }
        String createdAt = registerRequestRequest.getCreatedAt() != null ? String.valueOf(registerRequestRequest.getCreatedAt()) : "";
        if (createdAt.isEmpty()) {
            notification.addError("Request createdAt is required");
        }
        String endDate = registerRequestRequest.getUpdatedAt() != null ? String.valueOf(registerRequestRequest.getUpdatedAt()) : "";
        if (endDate.isEmpty()) {
            notification.addError("Request updatedAt is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
