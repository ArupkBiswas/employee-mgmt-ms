package com.example.employee_mgmt_ms.exception.handler;

import com.example.employee_mgmt_ms.exception.base.DataNotFoundException;
import com.example.employee_mgmt_ms.exception.base.DuplicateDataFoundException;
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

    @ExceptionHandler(DuplicateDataFoundException.class)
    public ResponseEntity<Object> handleDuplicateDataFoundException(DuplicateDataFoundException ex) {
        // Log the exception and return a user-friendly message
        System.err.println("Duplicate data found: " + ex.getErrorMessage());
        return ResponseEntity.status(409)
                .body("Duplicate data found: " + ex.getErrorMessage());
    }
}
