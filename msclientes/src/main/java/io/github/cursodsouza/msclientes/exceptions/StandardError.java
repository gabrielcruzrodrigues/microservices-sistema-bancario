package io.github.cursodsouza.msclientes.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StandardError {
    private LocalDateTime timeStamp;
    private Integer status;
    private String fieldError;
    private String path;
    private String error;

    public StandardError(LocalDateTime timeStamp, Integer status, String fieldError, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.fieldError = fieldError;
        this.path = path;
    }
}
