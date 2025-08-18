package br.com.antonio.projeto.entity;

import br.com.antonio.projeto.dto.PermissaoPerfilRescursoDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "PERMISSAO_PERFIL_RECURSO")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PermissaoPerfilRecursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PERFIL")
    private PerfilEntity perfil;

    @ManyToOne
    @JoinColumn(name = "ID_RECURSO")
    private RecursoEntity recurso;

    public PermissaoPerfilRecursoEntity(PermissaoPerfilRescursoDTO permissaoPerfil){
        BeanUtils.copyProperties(permissaoPerfil, this);
        if (permissaoPerfil != null && permissaoPerfil.getRecurso() != null){
            this.recurso = new RecursoEntity(permissaoPerfil.getRecurso());
        }
        if (permissaoPerfil != null && permissaoPerfil.getPerfil() != null) {
            this.perfil = new PerfilEntity(permissaoPerfil.getPerfil());
        }
    }
}
