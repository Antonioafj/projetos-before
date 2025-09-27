package br.com.antonio.vendas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable {

    private static final long serialVerisonUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String codigoBarras;
    private String unidadeMedida;
    private Double estoque = 0.00;
    private Double precoCusto = 0.00;
    private Double precoVenda = 0.00;
    private Double lucro = 0.00;
    private Double margemLucro = 0.00;
}
