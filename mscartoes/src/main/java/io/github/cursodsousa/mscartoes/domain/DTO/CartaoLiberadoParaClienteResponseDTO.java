package io.github.cursodsousa.mscartoes.domain.DTO;

import io.github.cursodsousa.mscartoes.domain.CartaoCliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartaoLiberadoParaClienteResponseDTO {
    private String name;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartaoLiberadoParaClienteResponseDTO fromModel(CartaoCliente model) {
        return new CartaoLiberadoParaClienteResponseDTO(
                model.getCartao().getName(),
                model.getCartao().getBandeira().toString(),
                model.getLimite()
        );
    }
}
