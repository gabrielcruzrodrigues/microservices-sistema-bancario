package io.github.cursodsousa.mscartoes.domain.DTO;

import io.github.cursodsousa.mscartoes.domain.BandeiraCartaoEnum;
import io.github.cursodsousa.mscartoes.domain.Cartao;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CartaoRegisterDTO {
    private String name;
    private BandeiraCartaoEnum bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao toModel() {
        return new Cartao(this.name, this.bandeira, this.renda, this.limiteBasico);
    }
}
