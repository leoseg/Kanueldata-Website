package com.example.kanuledatawebsite.exceptionhandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Extends the default Spring Boot Exception Handler
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles all exceptions thrown
     * @param ex Exception thrown
     * @param request request in which exception was thrown
     * @return Response with the error text name in the message
     */
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        return new ResponseEntity<>( "Internal Server Error: " +ex.getClass().getName()+" occured", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

