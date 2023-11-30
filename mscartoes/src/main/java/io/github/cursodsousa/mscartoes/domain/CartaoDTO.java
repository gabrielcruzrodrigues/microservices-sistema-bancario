package io.github.cursodsousa.mscartoes.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CartaoDTO {
    private String name;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao toModel() {
        return new Cartao(this.name, this.bandeira, this.renda, this.limiteBasico);
    }
}
