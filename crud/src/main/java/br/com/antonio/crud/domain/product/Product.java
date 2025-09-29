package br.com.antonio.crud.domain.product;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "product")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Integer price_in_cents;


    public Product(ResquestProduct resquestProduct) {
        this.name = resquestProduct.name();
        this.price_in_cents = resquestProduct.price_in_cents();
    }
}
