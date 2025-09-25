package br.com.antonio.vendas.repository;

import br.com.antonio.vendas.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
