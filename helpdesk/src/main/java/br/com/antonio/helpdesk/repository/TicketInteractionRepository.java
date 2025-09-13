package br.com.antonio.helpdesk.repository;

import br.com.antonio.helpdesk.entity.TicketEntity;
import br.com.antonio.helpdesk.entity.TicketInteractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketInteractionRepository extends JpaRepository<TicketInteractionEntity, UUID> {

    List<TicketInteractionEntity> findByTicket(TicketEntity ticket);

}
