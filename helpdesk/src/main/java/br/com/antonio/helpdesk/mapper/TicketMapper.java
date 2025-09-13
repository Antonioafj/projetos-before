package br.com.antonio.helpdesk.mapper;

import br.com.antonio.helpdesk.domain.Ticket;
import br.com.antonio.helpdesk.domain.TicketInteraction;
import br.com.antonio.helpdesk.dto.CreateTicketInteractionDto;
import br.com.antonio.helpdesk.dto.TicketDto;
import br.com.antonio.helpdesk.dto.TicketInteractionDto;
import br.com.antonio.helpdesk.entity.TicketEntity;
import br.com.antonio.helpdesk.entity.TicketInteractionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy =  ReportingPolicy.IGNORE)
public interface TicketMapper {
    Ticket toDomain(TicketEntity entity);
    TicketDto toDto(Ticket domain);
    TicketEntity toEntity(Ticket domain);
    // Remova: Ticket toDomain(CreatedTicketDto dto);

    TicketInteraction toDomain(CreateTicketInteractionDto dto);

    List<Ticket> toDomain(List<TicketEntity> entities);

    List<TicketDto> toDto(List<Ticket> domains);

    List<TicketInteractionDto> toInteractionDto(List<TicketInteraction> domains);

    List<TicketInteraction> toInterectionDomain(List<TicketInteractionEntity> byTicket);


}
