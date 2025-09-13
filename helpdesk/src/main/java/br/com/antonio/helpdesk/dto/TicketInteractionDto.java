package br.com.antonio.helpdesk.dto;

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
public class TicketInteractionDto {

    private UUID id;

    private String message;

    private TicketStatus status;

    private List<AttachmentDto> attachments;

    private UserDTO sendByUser;

    private Date createAt;

}






































