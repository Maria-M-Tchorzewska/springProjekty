package com.example.BookStoreFinalProject.exceptions;

import com.example.BookStoreFinalProject.dtos.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleUnexpected(Exception ex) {
        Response response = Response.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Coś poszło bardzo nie tak: " + ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> handleMissing(ResourceNotFoundException ex) {
        Response response = Response.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Nie znaleziono: " + ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingValueException.class)
    public ResponseEntity<Response> handleEmptyField(MissingValueException ex) {
        Response response = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Brak wymaganych danych: " + ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<Response> handleLoginError(InvalidLoginException ex) {
        Response response = Response.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Niepoprawne dane logowania: " + ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidOrderStateException.class)
    public ResponseEntity<Response> handleBadOrder(InvalidOrderStateException ex) {
        Response response = Response.builder()
                .status(HttpStatus.CONFLICT.value())
                .message("Błąd stanu zamówienia: " + ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
