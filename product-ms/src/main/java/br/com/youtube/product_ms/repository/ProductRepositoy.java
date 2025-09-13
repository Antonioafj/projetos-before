package br.com.youtube.product_ms.repository;

import br.com.youtube.product_ms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoy extends JpaRepository<Product, Long> {
}
