package com.frikiteam.eventsboot.domain.values;

import lombok.Value;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Value(staticConstructor = "of")
public class OfferId implements Serializable {
    private UUID value;

    private OfferId(UUID value) {
        this.value = value;
    }

    protected OfferId() {
        this.value = UUID.randomUUID();
    }
}
