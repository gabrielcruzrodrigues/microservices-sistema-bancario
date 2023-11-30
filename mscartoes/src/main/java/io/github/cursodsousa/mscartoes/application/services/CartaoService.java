package io.github.cursodsousa.mscartoes.application.services;

import io.github.cursodsousa.mscartoes.domain.Cartao;
import io.github.cursodsousa.mscartoes.domain.CartaoDTO;
import io.github.cursodsousa.mscartoes.exceptions.CartaoNotFoundException;
import io.github.cursodsousa.mscartoes.infra.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository cartaoRepository;

    @Transactional
    public Cartao save(CartaoDTO cartao) {
        return cartaoRepository.save(cartao.toModel());
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda) {
        var rendaBidDecimal = BigDecimal.valueOf(renda);
        return cartaoRepository.findByRendaLessThanEqual(rendaBidDecimal);
    }

    public List<Cartao> getAllCartoes() {
        return cartaoRepository.findAll();
    }

    public Cartao findById(Long id) {
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        return cartao.orElseThrow(CartaoNotFoundException::new);
    }
}
