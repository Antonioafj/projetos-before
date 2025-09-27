package br.com.antonio.vendas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "itens_entrada")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntrada implements Serializable {

    private static final long serialVerisonUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double quantidade;
    private Double valorCusto;
    private Double valor;
    @ManyToOne
    private Entrada entrada;
    @ManyToOne
    private Produto produto;

}
