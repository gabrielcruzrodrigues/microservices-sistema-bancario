package io.github.cursodsouza.msclientes.applications.controllers;

import com.ctc.wstx.shaded.msv_core.util.Uri;
import io.github.cursodsouza.msclientes.applications.services.ClientService;
import io.github.cursodsouza.msclientes.domain.Client;
import io.github.cursodsouza.msclientes.domain.DTO.ClientDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public String status() {
        log.info("obtendo o status do microservic3e de clientes");
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody ClientDTO request) {
        Client client = clientService.save(request);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity clientData(@RequestParam("cpf") String cpf) {
        return ResponseEntity.ok().body(clientService.getByCpf(cpf));
    }
}
