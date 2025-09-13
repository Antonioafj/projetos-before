package br.com.antonio.helpdesk.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO{

    UUID id;
    String username;
    String name;
    String email;
    Date createAt;
}
