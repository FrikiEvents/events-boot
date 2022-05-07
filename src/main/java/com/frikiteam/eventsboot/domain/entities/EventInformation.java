package com.frikiteam.eventsboot.domain.entities;

import com.frikiteam.eventsboot.domain.values.EventId;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "events_information")
public class EventInformation {
  @EmbeddedId
  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "BINARY(16)"))
  })
  private EventId id;

  @Column(name = "event_description")
  private String EventDescription;

  @Column(name = "event_image")
  private String EventImage;

  @Column(name = "event_link")
  private String EventLink;

  @Column(name = "start_date")
  private LocalDateTime StartDate;

  @Column(name = "end_date")
  private LocalDateTime EndDate;
}
