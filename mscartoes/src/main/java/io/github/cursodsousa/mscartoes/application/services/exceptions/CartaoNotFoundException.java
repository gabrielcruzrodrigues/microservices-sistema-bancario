package io.github.cursodsousa.mscartoes.application.services.exceptions;

public class CartaoNotFoundException extends RuntimeException {
    public CartaoNotFoundException() {
        super("Cartão não encontrado!");
    }
}
