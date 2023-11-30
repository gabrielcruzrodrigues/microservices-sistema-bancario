package io.github.cursodsouza.msavaliadorcredito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnAvaliacaoCliente {
    private List<CartaoAprovado> cartoes;
}
