package br.com.antonio.vendas.repository;

import br.com.antonio.vendas.models.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {
}
