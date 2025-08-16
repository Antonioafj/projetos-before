package br.com.antonio.projeto.service;


import br.com.antonio.projeto.dto.RecursoDTO;
import br.com.antonio.projeto.entity.RecursoEntity;
import br.com.antonio.projeto.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository repository;

    public List<RecursoDTO> listarTodos() {
        List<RecursoEntity> recursos = repository.findAll();
        return recursos.stream().map(RecursoDTO::new).toList();
    }

    public void inserir(RecursoDTO recurso) {
        RecursoEntity recursoEntity = new RecursoEntity(recurso);
        repository.save(recursoEntity);
    }

    public RecursoDTO alterar(RecursoDTO recurso){
        RecursoEntity recursoEntity = new RecursoEntity(recurso);
        var entity = repository.save(recursoEntity);
        return new RecursoDTO(entity);
    }

    public void exculir(Long id){
        RecursoEntity recursoEntity = repository.findById(id).get();
        repository.delete(recursoEntity);
    }

    public RecursoDTO buscarPorId(Long id){
        return new RecursoDTO(repository.findById(id).get());
    }
}


















