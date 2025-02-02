package com.gemr.accounts_api.exception;

import com.gemr.accounts_api.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ApiError apiError = new ApiError("error", exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ApiError> handleResourceAlreadyExistException(ResourceAlreadyExistException exception) {
        ApiError apiError = new ApiError("error", exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        // Mapping the field errors to the validation error format
        List<ApiError.ValidationError> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ApiError.ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        // Constructing the ApiError response
        ApiError apiError = new ApiError("error", "Validation errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}
