package com.frikiteam.eventsboot.domain.entities;

import com.frikiteam.eventsboot.domain.values.EventId;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "events_information")
<<<<<<< HEAD
@Entity
=======
>>>>>>> 9521582eeff3759e7d292c17925490a2dfff4e4d
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
}
