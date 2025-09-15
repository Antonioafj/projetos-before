package br.com.youtube.product_ms.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.youtube.product_ms.dto.ProductDTO;

public class ProductTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(ProductDTO.class).addTemplate("valid", new Rule() {{
            add("name", random("Iphone 14 Pro Max", "Iphone 13 Pro Max", "Samsung S23 Ultra"));
            add("description", "Celular de últma geração e tals. Parte da frente em Ceramic Shield, Parte de Aluminio");
            add("price", random(Double.class, range(0.01, 7997.99)));

        }});
    }
}
