package com.frikiteam.eventsboot.api;

import com.frikiteam.eventsboot.command.application.dtos.EventInformationView;
import com.frikiteam.eventsboot.command.application.dtos.RegisterEventInformationRequest;
import com.frikiteam.eventsboot.command.application.dtos.RegisterEventInformationResponse;
import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.command.application.notification.Result;
import com.frikiteam.eventsboot.command.application.services.EventInformationApplicationService;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventinformations")

public class EventInformationController {
    private final EventInformationApplicationService personApplicationService;

    public EventInformationController(EventInformationApplicationService personApplicationService) {
        this.personApplicationService = personApplicationService;
    }

    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerEventInformation(@RequestBody RegisterEventInformationRequest registerEventInformationRequest) {
        try {
            Result<RegisterEventInformationResponse, Notification> result = personApplicationService.registerEventInformation(registerEventInformationRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }

    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Get client person by id", response = EventInformationView.class)
    public ResponseEntity<Object> getById(@PathVariable("id") String id) {
        try {
            EventInformationView personView = personApplicationService.getById(id);
            return ApiController.ok(personView);
        } catch(Exception e) {
            e.printStackTrace();
            return ApiController.serverError();
        }
    }
}
