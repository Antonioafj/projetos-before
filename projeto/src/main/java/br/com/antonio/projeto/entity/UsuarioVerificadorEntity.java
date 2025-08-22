package br.com.antonio.projeto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "USUARIO_VERIFICADOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioVerificadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID uuid;

    @Column(nullable = false)
    private Instant dataExpiracao;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
    private UsuarioEntity usuario;
}
