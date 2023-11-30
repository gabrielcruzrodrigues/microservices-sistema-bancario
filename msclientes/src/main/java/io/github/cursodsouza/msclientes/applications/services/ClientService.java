package io.github.cursodsouza.msclientes.applications.services;

import io.github.cursodsouza.msclientes.applications.services.exceptions.ObjectNotFoundException;
import io.github.cursodsouza.msclientes.domain.Client;
import io.github.cursodsouza.msclientes.domain.DTO.ClientDTO;
import io.github.cursodsouza.msclientes.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client save(ClientDTO client) {
        return clientRepository.save(client.toModel());
    }

    public Client getByCpf(String cpf) {
        Optional<Client> client = clientRepository.findByCpf(cpf);
        return client.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!"));
    }
}
