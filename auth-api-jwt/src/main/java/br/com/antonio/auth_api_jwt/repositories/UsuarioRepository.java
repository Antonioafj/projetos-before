package br.com.antonio.auth_api_jwt.repositories;

import br.com.antonio.auth_api_jwt.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}
