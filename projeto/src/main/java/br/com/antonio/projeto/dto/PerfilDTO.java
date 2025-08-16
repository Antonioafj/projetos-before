package br.com.antonio.projeto.dto;

import br.com.antonio.projeto.entity.PerfilEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class PerfilDTO {

    private Long id;
    private String descricao;

    public PerfilDTO(PerfilEntity perfil) {
        BeanUtils.copyProperties(perfil, this);
    }

}
