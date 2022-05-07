package com.frikiteam.eventsboot.command.application.queries;

import lombok.Data;

@Data
public class GetEventInformationByIdQuery {
    private String eventInformationId;

    public GetEventInformationByIdQuery(String eventInformationId) {
        this.eventInformationId = eventInformationId;
    }
}
