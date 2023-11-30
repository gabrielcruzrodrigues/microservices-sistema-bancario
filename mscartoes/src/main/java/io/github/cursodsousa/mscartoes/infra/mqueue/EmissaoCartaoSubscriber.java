package io.github.cursodsousa.mscartoes.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cursodsousa.mscartoes.application.services.CartaoService;
import io.github.cursodsousa.mscartoes.domain.Cartao;
import io.github.cursodsousa.mscartoes.domain.ClienteCartao;
import io.github.cursodsousa.mscartoes.domain.DadosSolicitacaoEmissaoCartao;
import io.github.cursodsousa.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmissaoCartaoSubscriber {

    @Autowired
    private CartaoService cartaoServices;

    @Autowired
    private ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queue.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) throws JsonProcessingException{
        try {
            DadosSolicitacaoEmissaoCartao cartao = convertIntoDadosSolicitacaoEmissaoCartao(payload);
            cadastrarNovoCartao(cartao);
        } catch (Exception ex) {
            log.error("Error ao receber solicitação de novo cartão: {}", ex.getMessage());
        }
    }

    private DadosSolicitacaoEmissaoCartao convertIntoDadosSolicitacaoEmissaoCartao(String payload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
    }

    private void cadastrarNovoCartao(DadosSolicitacaoEmissaoCartao solicitacaoEmissaoCartao) {
        Cartao cartao = cartaoServices.findById(solicitacaoEmissaoCartao.getIdCartao());
        ClienteCartao clienteCartao = new ClienteCartao();
        clienteCartao.setCartao(cartao);
        clienteCartao.setCpf(solicitacaoEmissaoCartao.getCpf());
        clienteCartao.setLimite(solicitacaoEmissaoCartao.getLimiteLiberado());
        clienteCartaoRepository.save(clienteCartao);
    }


}
