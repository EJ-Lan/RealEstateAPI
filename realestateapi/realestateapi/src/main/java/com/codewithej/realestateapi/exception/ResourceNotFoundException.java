package com.codewithej.realestateapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class annotated with {@code @ResponseStatus} to indicate a resource was not found.
 * <p>
 * This exception can be thrown from anywhere within the application to indicate that a requested resource
 * does not exist. It automatically triggers the response status specified.
 * </p>
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}