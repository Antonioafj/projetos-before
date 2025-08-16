package br.com.antonio.projeto.repository;

import br.com.antonio.projeto.entity.RecursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecursoRepository extends JpaRepository<RecursoEntity, Long> {
}
