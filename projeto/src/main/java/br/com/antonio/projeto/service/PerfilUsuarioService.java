package br.com.antonio.projeto.service;

import br.com.antonio.projeto.dto.PerfilUsuarioDTO;
import br.com.antonio.projeto.entity.PerfilUsuarioEntity;
import br.com.antonio.projeto.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilUsuarioService {

    @Autowired
    private PerfilUsuarioRepository repository;

    public List<PerfilUsuarioDTO> listarTodos() {
        List<PerfilUsuarioEntity> perfilUsuarios = repository.findAll();
        return perfilUsuarios.stream().map(PerfilUsuarioDTO::new).toList();
    }

    public void inserir(PerfilUsuarioDTO perfilUsuario) {
        PerfilUsuarioEntity perfilUsuarioEntity = new PerfilUsuarioEntity(perfilUsuario);
        repository.save(perfilUsuarioEntity);
    }

    public PerfilUsuarioDTO alterar(PerfilUsuarioDTO perfilUsuario){
        PerfilUsuarioEntity perfilUsuarioEntity = new PerfilUsuarioEntity(perfilUsuario);
        var entity = repository.save(perfilUsuarioEntity);
        return new PerfilUsuarioDTO(entity);
    }

    public void excluir(Long id){
        PerfilUsuarioEntity perfilUsuarioEntity = repository.findById(id).get();
        repository.delete(perfilUsuarioEntity);
    }

    public PerfilUsuarioDTO buscarPorId(Long id){
        return new PerfilUsuarioDTO(repository.findById(id).get());
    }
}
