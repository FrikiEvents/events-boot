package com.frikiteam.eventsboot.command.application.queries;

import lombok.Data;

@Data
public class GetRequestByIdQuery {
    private String requestId;

    public GetRequestByIdQuery(String requestId) {
        this.requestId = requestId;
    }
}
