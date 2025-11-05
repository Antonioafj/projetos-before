package br.com.antonio.apideteste.repository;

import br.com.antonio.apideteste.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuario extends JpaRepository<Usuario, Integer> {
}
