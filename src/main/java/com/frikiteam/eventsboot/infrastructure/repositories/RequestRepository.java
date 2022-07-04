package com.frikiteam.eventsboot.infrastructure.repositories;
import com.frikiteam.eventsboot.domain.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequestRepository<T extends Request> extends JpaRepository<T, UUID> {
}

