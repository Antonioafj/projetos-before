package br.com.antonio.auth_api.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Table(name = "products")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product implements Serializable {

    private static final long serialVerisonUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Integer price_in_cents;


    public Product(ResquestProduct resquestProduct) {
        this.id = resquestProduct.id();
        this.name = resquestProduct.name();
        this.price_in_cents = resquestProduct.price_in_cents();
    }
}
