package br.com.antonio.apideteste.controller;


import br.com.antonio.apideteste.dto.UsuarioDto;
import br.com.antonio.apideteste.model.Usuario;
import br.com.antonio.apideteste.security.Token;
import br.com.antonio.apideteste.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {


    private UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listaUsuarios() {
        List<Usuario> lista = usuarioService.listarUsuario();
        return ResponseEntity.status(200).body(lista);
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario (@Valid @RequestBody Usuario usuario) {
        Usuario usuarioNovo = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(201).body(usuarioNovo);
    }

    @PutMapping
    public ResponseEntity<Usuario> editarUsuario (@Valid @RequestBody Usuario usuario) {
        Usuario usuarioNovo = usuarioService.editarUsuario(usuario);
        return ResponseEntity.status(200).body(usuarioNovo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable("id") Integer id) {
        usuarioService.excluirUsuario(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Token> validarSenha(@Valid @RequestBody UsuarioDto usuario) {
        Token token = usuarioService.gerarToken(usuario);
        if (token != null) {
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(403).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}





















