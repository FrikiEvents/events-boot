package com.frikiteam.eventsboot.domain.values;

import lombok.Value;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
@Value(staticConstructor = "of")
public class EventId {
    private UUID value;

    private EventId(UUID value) {
        this.value = value;
    }

    protected EventId() {
        this.value = UUID.randomUUID();;
    }
}
