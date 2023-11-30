package io.github.cursodsousa.mscartoes.application.services;

import io.github.cursodsousa.mscartoes.domain.CartaoCliente;
import io.github.cursodsousa.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository clienteCartaoRepository;

    public List<CartaoCliente> listarCartoesPorCpf(String cpf) {
        return clienteCartaoRepository.findByCpf(cpf);
    }
}
