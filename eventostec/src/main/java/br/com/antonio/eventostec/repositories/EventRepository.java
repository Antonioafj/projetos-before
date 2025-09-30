package br.com.antonio.eventostec.repositories;

import br.com.antonio.eventostec.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
