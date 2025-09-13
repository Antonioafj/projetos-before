package br.com.antonio.helpdesk.domain;

import br.com.antonio.helpdesk.dto.AttachmentDto;
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
public class Ticket {
    private UUID id;
    private User supportUser;
    private String subject;
    private String description;
    private TicketStatus status;
    private User createBy;
    private List<Attachment> attachments;
    private Date createAt;
    private UUID updatedBy;
    private Date  updatedAt;
}
