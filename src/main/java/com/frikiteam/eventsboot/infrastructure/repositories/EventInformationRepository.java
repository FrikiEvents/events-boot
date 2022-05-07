package com.frikiteam.eventsboot.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.frikiteam.eventsboot.domain.entities.EventInformation;

import java.util.UUID;

@Repository
public interface EventInformationRepository<T extends EventInformation> extends JpaRepository<T, UUID> {
}
