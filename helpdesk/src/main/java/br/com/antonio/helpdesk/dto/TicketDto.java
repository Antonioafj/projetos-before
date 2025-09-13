package br.com.antonio.helpdesk.dto;

import br.com.antonio.helpdesk.entity.UserEntity;
import br.com.antonio.helpdesk.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private UUID id;

    private UserDTO supportUser;

    private String subject;

    private String description;

    private TicketStatus status;

    private UserDTO createBy;

    private Date createAt;

    private UUID updatedBy;

    private Date  updatedAt;

}
