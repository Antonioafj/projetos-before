package br.com.antonio.vendas.repository;

import br.com.antonio.vendas.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
