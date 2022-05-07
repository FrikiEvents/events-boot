package com.frikiteam.eventsboot.config;

import com.frikiteam.eventsboot.domain.entities.Place;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
  @Bean
  public Repository<Place> eventSourcingRepository(EventStore eventStore) {
    return EventSourcingRepository.builder(Place.class)
        .eventStore(eventStore)
        .build();
  }
}