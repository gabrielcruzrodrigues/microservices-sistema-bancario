package io.github.cursodsouza.msavaliadorcredito.application.service;

import feign.FeignException;
import io.github.cursodsouza.msavaliadorcredito.application.exceptions.ComunicationErrorMicroserviceException;
import io.github.cursodsouza.msavaliadorcredito.application.exceptions.ErrorSolicitacaoCartaoException;
import io.github.cursodsouza.msavaliadorcredito.application.exceptions.NotFoundExceptionClientData;
import io.github.cursodsouza.msavaliadorcredito.domain.Cartao;
import io.github.cursodsouza.msavaliadorcredito.domain.CartaoCliente;
import io.github.cursodsouza.msavaliadorcredito.domain.DTO.*;
import io.github.cursodsouza.msavaliadorcredito.domain.SituacaoCliente;
import io.github.cursodsouza.msavaliadorcredito.infra.clients.CartoesResourcesClient;
import io.github.cursodsouza.msavaliadorcredito.infra.clients.ClientesResourcesClient;
import io.github.cursodsouza.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClientesResourcesClient clientesResourcesClient;
    private final CartoesResourcesClient cartoesResourcesClient;
    private final SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws NotFoundExceptionClientData, ComunicationErrorMicroserviceException {

        try {
            ResponseEntity<DadosClienteDTO> dadosClienteResponse = clientesResourcesClient.clientData(cpf);
            ResponseEntity<List<CartaoCliente>> dadosCartaoResponse = cartoesResourcesClient.getCartoesByCliente(cpf);
            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(dadosCartaoResponse.getBody())
                    .build();

        } catch (FeignException.FeignClientException ex) {
            if (HttpStatus.NOT_FOUND.value() == ex.status()) throw new NotFoundExceptionClientData();
            throw new ComunicationErrorMicroserviceException(ex.getMessage(), ex.status());
        }
    }

    public ReturnAvaliacaoClienteDTO realizarAvaliacao(DadosAvaliacaoDTO dadosAvaliacaoDTO) throws ComunicationErrorMicroserviceException, NotFoundExceptionClientData {
        try {
            ResponseEntity<DadosClienteDTO> dadosClienteResponse = clientesResourcesClient.clientData(dadosAvaliacaoDTO.getCpf());
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesResourcesClient.getCartoesRendaAte(dadosAvaliacaoDTO.getRenda());

            List<Cartao> cartoes = cartoesResponse.getBody();
            List<CartaoAprovadoDTO> cartaoAprovadoDTOS = cartoes.stream().map(cartao -> {

                DadosClienteDTO dadosClienteDTO = dadosClienteResponse.getBody();
                assert dadosClienteDTO != null;

                CartaoAprovadoDTO aprovado = new CartaoAprovadoDTO();
                aprovado.setCartao(cartao.getName());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(calculoDeLimite(cartao, dadosAvaliacaoDTO, dadosClienteDTO));
                return aprovado;
            }).collect(Collectors.toList());

            return new ReturnAvaliacaoClienteDTO(cartaoAprovadoDTOS);

        } catch (FeignException.FeignClientException ex) {
            if (HttpStatus.NOT_FOUND.value() == ex.status()) throw new NotFoundExceptionClientData();
            throw new ComunicationErrorMicroserviceException(ex.getMessage(), ex.status());
        }
    }

    private BigDecimal calculoDeLimite(Cartao cartao, DadosAvaliacaoDTO dadosParaAvaliacao, DadosClienteDTO dadosClienteDTO) {
        BigDecimal limiteBasico = cartao.getLimiteBasico();
        BigDecimal idadeBd = BigDecimal.valueOf(dadosClienteDTO.getAge());
        var fator = idadeBd.divide(BigDecimal.valueOf(10));
        return fator.multiply(limiteBasico);
    }

    public ProtocoloSolicitacaoCartaoDTO solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartaoDTO cartao) {
        try {
            emissaoCartaoPublisher.solicitarCartao(cartao);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartaoDTO(protocolo);
        } catch (Exception ex) {
            throw new ErrorSolicitacaoCartaoException(ex.getMessage());
        }
    }
}
