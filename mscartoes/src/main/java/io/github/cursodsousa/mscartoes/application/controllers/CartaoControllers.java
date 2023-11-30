package io.github.cursodsousa.mscartoes.application.controllers;

import io.github.cursodsousa.mscartoes.application.services.CartaoService;
import io.github.cursodsousa.mscartoes.application.services.ClienteCartaoService;
import io.github.cursodsousa.mscartoes.domain.Cartao;
import io.github.cursodsousa.mscartoes.domain.DTO.CartaoRegisterDTO;
import io.github.cursodsousa.mscartoes.domain.DTO.CartaoLiberadoParaClienteResponseDTO;
import io.github.cursodsousa.mscartoes.domain.CartaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartaoControllers {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Cartao>> getAll() {
        return ResponseEntity.ok().body(cartaoService.getAllCartoes());
    }

    @PostMapping
    public ResponseEntity<Object> cadastro(@RequestBody CartaoRegisterDTO request) {
        cartaoService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartaoLiberadoParaClienteResponseDTO>> getCartoesByCliente(@RequestParam("cpf") String cpf) {
        List<CartaoCliente> list = clienteCartaoService.listarCartoesPorCpf(cpf);
        List<CartaoLiberadoParaClienteResponseDTO> resultList = list.stream()
                .map(CartaoLiberadoParaClienteResponseDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(resultList);
    }
}
