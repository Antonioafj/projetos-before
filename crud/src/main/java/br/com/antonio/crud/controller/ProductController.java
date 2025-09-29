package br.com.antonio.crud.controller;


import br.com.antonio.crud.domain.product.Product;
import br.com.antonio.crud.domain.product.ProductRepository;
import br.com.antonio.crud.domain.product.ResquestProduct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts() {
        var allProducts = repository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
        public ResponseEntity registerProduct(@RequestBody @Valid ResquestProduct data) {

        var product = new Product(data);
        repository.save(product);

            return ResponseEntity.ok().build();
        }
}
