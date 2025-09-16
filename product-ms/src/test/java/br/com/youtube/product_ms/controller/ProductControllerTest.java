package br.com.youtube.product_ms.controller;


import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.youtube.product_ms.dto.ProductDTO;
import br.com.youtube.product_ms.repository.ProductRepositoy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ProductRepositoy repositoy;

    @BeforeAll
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.youtube.product_ms.fixture");
    }

    @Test
    public void shouldCreateProduct() throws Exception {
        ProductDTO request = Fixture.from(ProductDTO.class).gimme("valid");
        String content = mapper.writeValueAsString(request);

        mvc.perform(
                    MockMvcRequestBuilders.post("/products")
                            .header(AUTHORIZATION, "Bearer foo")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldNotCreateProduct() throws Exception {
        ProductDTO request = new ProductDTO();
        String content = mapper.writeValueAsString(request);

        mvc.perform(
                        MockMvcRequestBuilders.post("/products")
                                .header(AUTHORIZATION, "Bearer foo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldGetAllProducts() throws Exception {
        mvc.perform(get("/products")
                .header(AUTHORIZATION, "Bearer foo"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetById() throws Exception {

        Long id = repositoy.findAll().get(0).getId();

        mvc.perform(get("/products/{id}", id)
                        .header(AUTHORIZATION, "Bearer foo"))
                .andExpect(status().isOk());
    }
}


























