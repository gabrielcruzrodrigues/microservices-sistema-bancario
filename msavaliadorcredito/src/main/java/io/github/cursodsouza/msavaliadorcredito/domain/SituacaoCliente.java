package io.github.cursodsouza.msavaliadorcredito.domain;

import io.github.cursodsouza.msavaliadorcredito.domain.DTO.DadosClienteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SituacaoCliente {
    private DadosClienteDTO cliente;
    private List<CartaoCliente> cartoes;
}
