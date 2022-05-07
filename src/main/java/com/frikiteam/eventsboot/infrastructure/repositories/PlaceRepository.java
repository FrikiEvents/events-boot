package com.frikiteam.eventsboot.infrastructure.repositories;

import com.frikiteam.eventsboot.domain.entities.EventInformation;
import com.frikiteam.eventsboot.domain.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlaceRepository<T extends Place> extends JpaRepository<T, UUID> {
}

