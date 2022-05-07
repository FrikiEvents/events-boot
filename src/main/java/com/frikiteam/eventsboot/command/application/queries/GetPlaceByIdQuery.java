package com.frikiteam.eventsboot.command.application.queries;

import lombok.Data;

@Data
public class GetPlaceByIdQuery {
    private String placeId;

    public GetPlaceByIdQuery(String placeId) {
        this.placeId = placeId;
    }
}
