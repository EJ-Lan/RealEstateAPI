package com.codewithej.realestateapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * Provides global exception handling for controllers within the application. This class
 * intercepts exceptions thrown by controller methods and returns an appropriate ResponseEntity
 * object to the client.
 * <p>
 * The {@code @ControllerAdvice} annotation allows it to be applicable across all controllers,
 * enabling centralized exception handling.
 * </p>
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles cases where a resource is not found, wrapping the exception details
     * in a {@link ErrorDetails} object and returning it within a ResponseEntity.
     *
     * @param ex The caught ResourceNotFoundException.
     * @param request The web request during which the exception was thrown.
     * @return A ResponseEntity containing the error details and HTTP status code for not found.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * ErrorDetails is a private static class used within CustomExceptionHandler
     * to encapsulate information about an error in a structured format. This includes
     * a timestamp when the error occurred, a message describing the error, and details
     * about the web request during which the error occurred.
     */
    private static class ErrorDetails {
        private Date timestamp;
        private String message;
        private String details;

        /**
         * Constructs a new ErrorDetails object with the specified timestamp, message, and details.
         *
         * @param timestamp The time when the error occurred.
         * @param message A message describing the error.
         * @param details Details about the web request that resulted in the error.
         */
        public ErrorDetails(Date timestamp, String message, String details) {
            this.timestamp = timestamp;
            this.message = message;
            this.details = details;
        }

        /**
         * Sets the timestamp of when the error occurred.
         *
         * @param timestamp The timestamp to set for the error occurrence.
         */
        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

        /**
         * Retrieves the message describing the error.
         *
         * @return The error message.
         */
        public String getMessage() {
            return message;
        }

        /**
         * Sets the message describing the error.
         *
         * @param message The error message to set.
         */
        public void setMessage(String message) {
            this.message = message;
        }

        /**
         * Retrieves the details about the error context.
         *
         * @return The error details.
         */
        public String getDetails() {
            return details;
        }

        /**
         * Sets the details about the error context.
         *
         * @param details The error details to set.
         */
        public void setDetails(String details) {
            this.details = details;
        }
    }
}
