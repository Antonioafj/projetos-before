package br.com.antonio.helpdesk.controller;

import br.com.antonio.helpdesk.domain.User;
import br.com.antonio.helpdesk.dto.CreatedUserDto;
import br.com.antonio.helpdesk.dto.UserDTO;
import br.com.antonio.helpdesk.mapper.UserMapper;
import br.com.antonio.helpdesk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody CreatedUserDto created) {
        User user = mapper.toDomain(created);
        UserDTO createUser = mapper.toDto(userService.createUser(user));
        return ResponseEntity.ok(createUser);
    }
}
























