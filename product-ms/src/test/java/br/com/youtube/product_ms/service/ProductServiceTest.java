package br.com.youtube.product_ms.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.youtube.product_ms.dto.ProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:clear-database.sql"})
public class ProductServiceTest {

    @Autowired
    private ProductService service;

    @BeforeAll
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.youtube.product_ms.fixture");
    }

    @Test
    public void shouldCreateProduct() {
        ProductDTO request = Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = service.create(request);
        assertNotNull(response.get());
        assertEquals(request.getName(), response.get().getName());
        assertEquals(request.getDescription(), response.get().getDescription());
        assertEquals(request.getPrice(), response.get().getPrice());
        assertTrue(response.get().isAvailable());

    }

    @Test
    public void shouldGetAllProducts() {
        ProductDTO request = Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = service.create(request);
        List<ProductDTO> responses = service.getAll();

        assertNotNull(responses);
        assertEquals(responses.get(0).getName(), response.get().getName());
        assertEquals(responses.get(0).getDescription(), response.get().getDescription());
        assertEquals(responses.get(0).getPrice(), response.get().getPrice());


    }

    @Test
    public void shoukdGetById() {
        ProductDTO request = Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = service.create(request);

        Long id = response.get().getId();

        Optional<ProductDTO> responseById = service.getById(id);
        assertNotNull(responseById.get());

        assertEquals(request.getName(), responseById.get().getName());
        assertEquals(request.getDescription(), responseById.get().getDescription());
        assertEquals(request.getPrice(), responseById.get().getPrice());
        assertTrue(responseById.get().isAvailable());
    }
}




































