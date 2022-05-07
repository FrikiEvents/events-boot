package com.frikiteam.eventsboot.domain.entities;

import com.frikiteam.eventsboot.domain.values.EventId;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "events_information")
@Data
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

  public EventInformation(EventId id, String eventDescription, String eventImage, String eventLink, LocalDateTime startDate, LocalDateTime endDate) {
    this.id = id;
    EventDescription = eventDescription;
    EventImage = eventImage;
    EventLink = eventLink;
    StartDate = startDate;
    EndDate = endDate;
  }

  public EventInformation() {

  }
}
