package br.com.antonio.auth_api_jwt.models;


import br.com.antonio.auth_api_jwt.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="TB_USUARIO")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private String login;

    private String senha;

    private RoleEnum role;


    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

}
