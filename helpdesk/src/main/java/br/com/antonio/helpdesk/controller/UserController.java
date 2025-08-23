package br.com.antonio.helpdesk.controller;

import br.com.antonio.helpdesk.dto.CreatedUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @PostMapping
    public ResponseEntity create(@RequestBody CreatedUserDto created) {
        System.out.println("Usuario Criado");
        return ResponseEntity.noContent().build();
    }
}
