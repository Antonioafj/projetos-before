package br.com.antonio.projeto.service;

import br.com.antonio.projeto.dto.PermissaoPerfilRescursoDTO;
import br.com.antonio.projeto.entity.PermissaoPerfilRecursoEntity;
import br.com.antonio.projeto.repository.PermissaoPerfilRecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoPerfilRecursoService {

    @Autowired
    private PermissaoPerfilRecursoRepository permissaoPerfilRecurso;

    public List<PermissaoPerfilRescursoDTO> listarTodos() {
        List<PermissaoPerfilRecursoEntity> permissaoPerfilRecursos = permissaoPerfilRecurso.findAll();
        return permissaoPerfilRecursos.stream().map(PermissaoPerfilRescursoDTO::new).toList();
    }

    public void inserir(PermissaoPerfilRescursoDTO recurso) {
        PermissaoPerfilRecursoEntity recursoEntity = new PermissaoPerfilRecursoEntity(recurso);
        permissaoPerfilRecurso.save(recursoEntity);
    }

    public PermissaoPerfilRescursoDTO alterar(PermissaoPerfilRescursoDTO recurso){
        PermissaoPerfilRecursoEntity permissao= new PermissaoPerfilRecursoEntity(recurso);
        var entity = permissaoPerfilRecurso.save(permissao);
        return new PermissaoPerfilRescursoDTO(entity);
    }

    public void exculir(Long id){
        PermissaoPerfilRecursoEntity permissaoPerfilRecursoEntity = permissaoPerfilRecurso.findById(id).get();
        permissaoPerfilRecurso.delete(permissaoPerfilRecursoEntity);
    }

    public PermissaoPerfilRescursoDTO buscarPorId(Long id){
        return new PermissaoPerfilRescursoDTO(permissaoPerfilRecurso.findById(id).get());
    }

}
