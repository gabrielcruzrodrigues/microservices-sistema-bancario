package io.github.cursodsouza.msavaliadorcredito.application.exceptions;

import lombok.Getter;

public class ComunicationErrorMicroserviceException extends Exception {
    @Getter
    private Integer status;

    public ComunicationErrorMicroserviceException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
