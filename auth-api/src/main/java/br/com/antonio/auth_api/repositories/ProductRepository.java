package br.com.antonio.auth_api.repositories;

import br.com.antonio.auth_api.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String > {
}
