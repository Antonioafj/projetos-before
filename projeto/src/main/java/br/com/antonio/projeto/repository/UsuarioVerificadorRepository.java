package br.com.antonio.projeto.repository;

import br.com.antonio.projeto.entity.UsuarioVerificadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioVerificadorRepository extends JpaRepository<UsuarioVerificadorEntity, Long> {

        Optional<UsuarioVerificadorEntity> findByUuid(UUID uuid);
}
