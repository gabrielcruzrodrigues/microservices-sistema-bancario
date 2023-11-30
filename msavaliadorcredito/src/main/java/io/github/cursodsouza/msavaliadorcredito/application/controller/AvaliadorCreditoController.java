package io.github.cursodsouza.msavaliadorcredito.application.controller;

import io.github.cursodsouza.msavaliadorcredito.application.exceptions.ComunicationErrorMicroserviceException;
import io.github.cursodsouza.msavaliadorcredito.application.exceptions.ErrorSolicitacaoCartaoException;
import io.github.cursodsouza.msavaliadorcredito.application.exceptions.NotFoundExceptionClientData;
import io.github.cursodsouza.msavaliadorcredito.domain.model.*;
import io.github.cursodsouza.msavaliadorcredito.application.service.AvaliadorCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity<SituacaoCliente> consultarSituacaoCliente(@RequestParam("cpf") String cpf) throws NotFoundExceptionClientData, ComunicationErrorMicroserviceException {
        SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
        return ResponseEntity.ok().body(situacaoCliente);
    }

    @PostMapping
    public ResponseEntity<Object> realizarAvaliação(@RequestBody DadosAvaliacao dados) throws NotFoundExceptionClientData, ComunicationErrorMicroserviceException {
        ReturnAvaliacaoCliente returnAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacao(dados);
        return ResponseEntity.ok().body(returnAvaliacaoCliente);
    }

    @PostMapping("solicitacoes-cartao")
    public ResponseEntity<Object> solicitarEmissaoCartao(@RequestBody DadosSolicitacaoEmissaoCartao cartao) {
        try {
            ProtocoloSolicitacaoCartao protocolo = avaliadorCreditoService.solicitarEmissaoCartao(cartao);
            return ResponseEntity.ok().body(protocolo);
        } catch (ErrorSolicitacaoCartaoException ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
