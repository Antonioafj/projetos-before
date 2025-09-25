package br.com.antonio.vendas.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "entradas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entrada implements Serializable {

    private static final long serialVerisonUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String obs;
    private Double valorTotal=0.00;
    private Double quantidadeTotal=0.00;
    private Date dataEntrada = new Date();
    @ManyToOne
    private Fornecedor fornecedor;
    @ManyToOne
    private Funcionario funcionario;
}
