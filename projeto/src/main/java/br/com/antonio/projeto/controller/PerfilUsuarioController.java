package br.com.antonio.projeto.controller;

import br.com.antonio.projeto.dto.PerfilUsuarioDTO;
import br.com.antonio.projeto.service.PerfilUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/perfil-usuario")
public class PerfilUsuarioController {

    @Autowired
    private PerfilUsuarioService perfilService;

    @GetMapping
    public List<PerfilUsuarioDTO> listarTodos() {
        return perfilService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody PerfilUsuarioDTO perfil) {
        perfilService.inserir(perfil);
    }

    @PutMapping
    public PerfilUsuarioDTO alterar(@RequestBody  PerfilUsuarioDTO perfil) {
        return perfilService.alterar(perfil);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        perfilService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
