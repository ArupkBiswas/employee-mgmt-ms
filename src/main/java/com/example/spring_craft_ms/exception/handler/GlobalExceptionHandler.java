package com.example.spring_craft_ms.exception.handler;

import com.example.spring_craft_ms.exception.base.DataNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex) {
        // Log the exception and return a user-friendly message
        System.err.println("Data not found: " + ex.getErrorMessage());
        return ResponseEntity.status(404)
                .body("Data not found: " + ex.getErrorMessage());
    }
}
