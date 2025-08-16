package br.com.antonio.projeto.controller;

import br.com.antonio.projeto.dto.RecursoDTO;
import br.com.antonio.projeto.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/recurso")
@CrossOrigin
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    @GetMapping
    public List<RecursoDTO> listarTodos() {
        return recursoService.listarTodos();
    }

    @PostMapping
    public void inserir(@RequestBody RecursoDTO recurso) {
        recursoService.inserir(recurso);
    }

    @PutMapping
    public RecursoDTO alterar(RecursoDTO recurso){
        return recursoService.alterar(recurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        recursoService.exculir(id);
        return ResponseEntity.ok().build();
    }

}
