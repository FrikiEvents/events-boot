package com.frikiteam.eventsboot.domain.values;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class EventId implements Serializable {

  private UUID value;

  private EventId(UUID value) {
    this.value = value;
  }

  protected EventId() {
    this.value = UUID.randomUUID();
  }

}
