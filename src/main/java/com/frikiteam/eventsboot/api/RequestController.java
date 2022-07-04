package com.frikiteam.eventsboot.api;

import com.frikiteam.eventsboot.command.application.dtos.*;
import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.command.application.notification.Result;
import com.frikiteam.eventsboot.command.application.services.RequestApplicationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitudes")
public class RequestController {
    private final RequestApplicationService requestApplicationService;

    public RequestController(RequestApplicationService requestApplicationService) {
        this.requestApplicationService = requestApplicationService;
    }

    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerRequest(@RequestBody RegisterRequestRequest registerRequestRequest) {
        try {
            Result<RegisterRequestResponse, Notification> result =
                    requestApplicationService.registerRequest(registerRequestRequest);
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
            RequestView requestView = requestApplicationService.getById(id);
            return ApiController.ok(requestView);
        } catch(Exception e) {
            e.printStackTrace();
            return ApiController.serverError();
        }
    }
}
