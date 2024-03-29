package org.example.com.warehousemanager.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {
    private static final String MESSAGE_FORMAT = "%s with %s = %s not found";

    public <T> NotFoundException(Class<T> aClass, String key, String value) {
        super(HttpStatus.NOT_FOUND, MESSAGE_FORMAT.formatted(aClass.getSimpleName(), key, value));
    }
}
