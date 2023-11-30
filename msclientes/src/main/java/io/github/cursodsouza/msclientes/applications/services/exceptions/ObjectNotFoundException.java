package io.github.cursodsouza.msclientes.applications.services.exceptions;

import javax.persistence.EntityNotFoundException;
import javax.servlet.annotation.HttpConstraint;


public class ObjectNotFoundException extends EntityNotFoundException{
    public ObjectNotFoundException(String message) {
        super(message);
        }
}
