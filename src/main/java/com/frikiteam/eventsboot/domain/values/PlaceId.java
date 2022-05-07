package com.frikiteam.eventsboot.domain.values;

import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Value(staticConstructor = "of")
public class PlaceId implements Serializable {
    private UUID value;

    private PlaceId(UUID value) {
        this.value = value;
    }

    protected PlaceId() {
        this.value = UUID.randomUUID();
    }
}
