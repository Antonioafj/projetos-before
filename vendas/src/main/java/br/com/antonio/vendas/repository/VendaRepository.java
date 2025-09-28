package br.com.antonio.vendas.repository;

import br.com.antonio.vendas.models.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
