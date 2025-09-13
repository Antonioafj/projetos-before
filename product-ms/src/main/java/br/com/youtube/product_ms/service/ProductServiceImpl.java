package br.com.youtube.product_ms.service;

import br.com.youtube.product_ms.dto.ProductDTO;
import br.com.youtube.product_ms.model.Product;
import br.com.youtube.product_ms.repository.ProductRepositoy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepositoy repositoy;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Optional<ProductDTO> create(ProductDTO request) {
        Product product = mapper.map(request , Product.class);

        repositoy.saveAndFlush(product);

        ProductDTO response = mapper.map(product, ProductDTO.class);

        return Optional.of(response);
    }

    @Override
    public List<ProductDTO> getAll() {
        List<Product> products = repositoy.findAll();
        List<ProductDTO> responses = new ArrayList<>();

        products.forEach(p -> {
            ProductDTO response = mapper.map(p, ProductDTO.class);
            responses.add(response);
        });

        return responses;
    }

    @Override
    public Optional<ProductDTO> getById(Long id) {
        Optional<Product> product = repositoy.findById(id);
        if (product.isPresent()){
            Product productEntity = product.get();
            ProductDTO response = mapper.map(productEntity, ProductDTO.class);
            return Optional.of(response);
        }
        return Optional.empty();
    }

    @Override
    public boolean inactive(Long id) {
        Optional<Product> product = repositoy.findById(id);
        if (product.isPresent()) {
            product.get().setAvailable(false);
            return true;
        }
        return false;
    }
}
