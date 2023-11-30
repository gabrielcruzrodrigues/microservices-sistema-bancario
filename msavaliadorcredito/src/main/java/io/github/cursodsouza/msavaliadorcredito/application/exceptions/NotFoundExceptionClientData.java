package io.github.cursodsouza.msavaliadorcredito.application.exceptions;

public class NotFoundExceptionClientData extends Exception{
    public NotFoundExceptionClientData() {
        super("O cliente não foi encontrado!");
    }
}
