package br.com.antonio.projeto.entity;

import br.com.antonio.projeto.dto.PerfilDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "PERFIL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PerfilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    public PerfilEntity(PerfilDTO perfil) {
        BeanUtils.copyProperties(perfil, this);
    }
}
