package br.com.antonio.helpdesk.controller;

import br.com.antonio.helpdesk.domain.TicketInteraction;
import br.com.antonio.helpdesk.dto.CreateTicketInteractionDto;
import br.com.antonio.helpdesk.dto.CreatedTicketDto;
import br.com.antonio.helpdesk.dto.TicketDto;
import br.com.antonio.helpdesk.dto.TicketInteractionDto;
import br.com.antonio.helpdesk.mapper.TicketMapper;
import br.com.antonio.helpdesk.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper mapper;

    @PostMapping
    @Transactional
    public ResponseEntity<TicketDto> create(@RequestBody CreatedTicketDto created, Authentication authentication) {
        TicketDto createdTicket = mapper.toDto(ticketService.createTicket(created, authentication.getName()));
        return ResponseEntity.ok(createdTicket);
    }


    @PostMapping(value = "/{id}/interaction")
    @Transactional
    public ResponseEntity<TicketDto> create(@PathVariable(name = "id") UUID ticketId, @RequestBody CreateTicketInteractionDto request,
                                            Authentication authentication) {
        TicketInteraction domain = mapper.toDomain(request);
        domain.setTicketId(ticketId);
        TicketDto updatedTicket = mapper.toDto(ticketService.ticketInteract(domain, authentication.getName()));
        return ResponseEntity.ok(updatedTicket);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TicketDto> getById(@PathVariable(name = "id") UUID ticketId,
                                             Authentication authentication) {
        TicketDto ticket = mapper.toDto(ticketService.getById(ticketId));
        return ResponseEntity.ok(ticket);
    }


    @GetMapping(value = "/{id}/interactions")
    public ResponseEntity<List<TicketInteractionDto>> getInteractionsByTicketID(@PathVariable(name = "id") UUID ticketId,
                                                                                Authentication authentication) {
        List<TicketInteractionDto> ticket = mapper.toInteractionDto(ticketService.getInteractionsByTicketId(ticketId));
        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> listAllTickets() {
        List<TicketDto> tickets = mapper.toDto(ticketService.listAll());
        return ResponseEntity.ok(tickets);
    }
}
























