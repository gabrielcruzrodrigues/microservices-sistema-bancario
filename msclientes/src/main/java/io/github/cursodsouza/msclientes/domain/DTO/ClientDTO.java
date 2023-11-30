package io.github.cursodsouza.msclientes.domain.DTO;

import io.github.cursodsouza.msclientes.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String name ;
    private String cpf;
    private Integer age;

    public Client toModel() {
        return new Client(this.cpf, this.name, this.age);
    }
}
