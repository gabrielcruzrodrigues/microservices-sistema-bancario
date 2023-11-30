package io.github.cursodsousa.mscartoes.exceptions;

public class CartaoNotFoundException extends RuntimeException {
    public CartaoNotFoundException() {
        super("Cartão não encontrado!");
    }
}
