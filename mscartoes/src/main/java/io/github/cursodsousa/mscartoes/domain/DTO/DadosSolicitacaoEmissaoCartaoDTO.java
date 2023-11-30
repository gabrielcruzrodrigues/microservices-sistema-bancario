package io.github.cursodsousa.mscartoes.domain.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosSolicitacaoEmissaoCartaoDTO {
    private long idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}