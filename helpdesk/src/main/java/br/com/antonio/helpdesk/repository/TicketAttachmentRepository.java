package br.com.antonio.helpdesk.repository;

import br.com.antonio.helpdesk.entity.TicketAttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketAttachmentRepository extends JpaRepository<TicketAttachmentEntity, UUID> {
}
