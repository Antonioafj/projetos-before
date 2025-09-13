package br.com.antonio.helpdesk.domain;

import br.com.antonio.helpdesk.dto.AttachmentDto;
import br.com.antonio.helpdesk.dto.TicketDto;
import br.com.antonio.helpdesk.dto.UserDTO;
import br.com.antonio.helpdesk.entity.TicketEntity;
import br.com.antonio.helpdesk.entity.UserEntity;
import br.com.antonio.helpdesk.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketInteraction {

    private String message;

    private TicketStatus status;

    private UUID userId;

    private UUID ticketId;

    private UUID id;

    private Ticket ticket;

    private List<Attachment> attachments;

    private User sendByUser;

    private User createBy;

    private Date createAt;

    private UUID updatedBy;

    private Date updatedAt;
}






































