package io.github.cursodsouza.msavaliadorcredito.domain.DTO;

import io.github.cursodsouza.msavaliadorcredito.domain.DTO.CartaoAprovadoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnAvaliacaoClienteDTO {
    private List<CartaoAprovadoDTO> cartoes;
}
