package br.com.antonio.auth_api_jwt.controllers;


import br.com.antonio.auth_api_jwt.dtos.UsuarioDto;
import br.com.antonio.auth_api_jwt.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    private UsuarioDto salvar(@RequestBody UsuarioDto usuarioDto) {
        return usuarioService.salvar(usuarioDto);
    }
}
