package br.com.antonio.apideteste.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(message = "o nome deve ter no mínimo 3 caracteres")
    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @Email(message = "Ensira o email válido")
    @NotBlank(message = "O email é obrigatório")
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Column(name = "senha", columnDefinition = "TEXT", nullable = false)
    private String senha;

    @NotBlank(message = "O telefone é obrigatório")
    @Column(name = "telefone", length = 15, nullable = false)
    private String telefone;

}
