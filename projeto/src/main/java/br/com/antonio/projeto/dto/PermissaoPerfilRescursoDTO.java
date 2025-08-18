package br.com.antonio.projeto.dto;

import br.com.antonio.projeto.entity.PermissaoPerfilRecursoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class PermissaoPerfilRescursoDTO {

    private Long id;

    private PerfilDTO perfil;

    private RecursoDTO recurso;

    public PermissaoPerfilRescursoDTO(PermissaoPerfilRecursoEntity permissaoPerfilRecurso){
        BeanUtils.copyProperties(permissaoPerfilRecurso, this);
        if (permissaoPerfilRecurso != null && permissaoPerfilRecurso.getRecurso() != null){
            this.recurso = new RecursoDTO(permissaoPerfilRecurso.getRecurso());
        }
        if (permissaoPerfilRecurso != null && permissaoPerfilRecurso.getPerfil() != null){
            this.perfil = new PerfilDTO(permissaoPerfilRecurso.getPerfil());
        }
    }
}










































