package com.frikiteam.eventsboot.api;

import com.frikiteam.eventsboot.command.application.dtos.EventInformationView;
import com.frikiteam.eventsboot.command.application.dtos.PlaceView;
import com.frikiteam.eventsboot.command.application.dtos.RegisterPlaceRequest;
import com.frikiteam.eventsboot.command.application.dtos.RegisterPlaceResponse;

import com.frikiteam.eventsboot.command.application.notification.Notification;
import com.frikiteam.eventsboot.command.application.notification.Result;
import com.frikiteam.eventsboot.command.application.services.EventInformationApplicationService;
import com.frikiteam.eventsboot.command.application.services.PlaceApplicationService;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")

public class PlaceController {
    /*private final PlaceApplicationService placeApplicationServicee;

    public PlaceController(PlaceApplicationService pageApplicationService) {
        this.placeApplicationServicee = pageApplicationService;
    }

    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerPlace(@RequestBody RegisterPlaceRequest registerPlaceRequest) {
        try {
            Result<RegisterPlaceResponse, Notification> result = placeApplicationServicee.registerPlace(registerPlaceRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }*/

/*    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Get client person by id", response = EventInformationView.class)
    public ResponseEntity<Object> getById(@PathVariable("id") String id) {
        try {
            PlaceView personView = placeApplicationServicee.getById(id);
            return ApiController.ok(personView);
        } catch(Exception e) {
            e.printStackTrace();
            return ApiController.serverError();
        }
    }*/

}
