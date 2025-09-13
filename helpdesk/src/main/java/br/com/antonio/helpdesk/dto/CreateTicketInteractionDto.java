package br.com.antonio.helpdesk.dto;

import br.com.antonio.helpdesk.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketInteractionDto {

    private String message;

    private List<AttachmentDto> attachments;

}






































