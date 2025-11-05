package br.com.antonio.apideteste.controller;


import br.com.antonio.apideteste.repository.IUsuario;
import br.com.antonio.apideteste.model.Usuario;
import br.com.antonio.apideteste.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Usuario> criarUsuario (@RequestBody Usuario usuario) {
        Usuario usuarioNovo = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(201).body(usuarioNovo);
    }

    @PutMapping
    public ResponseEntity<Usuario> editarUsuario (@RequestBody Usuario usuario) {
        Usuario usuarioNovo = usuarioService.editarUsuario(usuario);
        return ResponseEntity.status(200).body(usuarioNovo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable("id") Integer id) {
        usuarioService.excluirUsuario(id);
        return ResponseEntity.status(204).build();
    }

}
