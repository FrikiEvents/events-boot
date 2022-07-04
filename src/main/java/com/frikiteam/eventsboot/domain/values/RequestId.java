package com.frikiteam.eventsboot.domain.values;

import lombok.Value;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Value(staticConstructor = "of")

public class RequestId implements Serializable {

    private UUID value;

    private RequestId(UUID value) {
        this.value = value;
    }

    protected RequestId() {
        this.value = UUID.randomUUID();
    }

}
