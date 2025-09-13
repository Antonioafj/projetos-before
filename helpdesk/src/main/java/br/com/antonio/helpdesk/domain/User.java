package br.com.antonio.helpdesk.domain;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User{
    UUID id;
    String username;
    String password;
    String name;
    String email;
    Date createAt;
}