package com.frikiteam.eventsboot.domain.values;

import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class PlaceId {
    private long value;

    private PlaceId(long value) {
        this.value = value;
    }

    protected PlaceId() {
        this.value = 0;
    }
}
