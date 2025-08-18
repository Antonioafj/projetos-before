package br.com.antonio.projeto.controller;

import br.com.antonio.projeto.dto.PermissaoPerfilRescursoDTO;
import br.com.antonio.projeto.service.PermissaoPerfilRecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/permissao-perfil-recurso")
@CrossOrigin
public class PermissaoPerfilRecursoController {

    @Autowired
    private PermissaoPerfilRecursoService permissaoPerfilRecursoService;

    @GetMapping
    public List<PermissaoPerfilRescursoDTO> listarTodos() {
        return permissaoPerfilRecursoService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody PermissaoPerfilRescursoDTO permissaoPerfilRescurso){
        permissaoPerfilRecursoService.inserir(permissaoPerfilRescurso);
    }

    @PutMapping
    public PermissaoPerfilRescursoDTO alterar(@RequestBody PermissaoPerfilRescursoDTO permissaoPerfilRescurso){
        return permissaoPerfilRecursoService.alterar(permissaoPerfilRescurso);
    }

    @DeleteMapping("/(id)")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        permissaoPerfilRecursoService.exculir(id);
        return ResponseEntity.ok().build();
    }
}

































