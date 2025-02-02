package com.gemr.accounts_api.exception;

import com.gemr.accounts_api.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Maneja excepciones cuando un recurso no se encuentra
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseError> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ResponseError responseError = new ResponseError("error", exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    // Maneja excepciones cuando un recurso ya existe
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ResponseError> handleResourceAlreadyExistException(ResourceAlreadyExistException exception) {
        ResponseError responseError = new ResponseError("error", exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseError);
    }

    // Maneja excepciones de validación de los parámetros de entrada
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        // Extrae los errores de validación
        List<ResponseError.ValidationError> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ResponseError.ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        ResponseError responseError = new ResponseError("error", "Validation errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

}
