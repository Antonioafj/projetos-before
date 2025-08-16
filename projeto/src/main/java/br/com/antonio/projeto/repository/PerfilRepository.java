package br.com.antonio.projeto.repository;

import br.com.antonio.projeto.entity.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
}
