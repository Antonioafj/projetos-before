package br.com.antonio.crud.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResquestProduct(

        String id,

        @NotBlank
        String name,

        @NotNull
        Integer price_in_cents
) {

}
