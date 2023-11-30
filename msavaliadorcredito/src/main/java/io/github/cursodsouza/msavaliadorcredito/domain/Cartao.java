package io.github.cursodsouza.msavaliadorcredito.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Cartao {
    private Long id;
    private String name;
    private String bandeira;
    private BigDecimal limiteBasico;
}
