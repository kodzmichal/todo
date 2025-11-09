package pl.kodz.todo.infrastructure.api;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import pl.kodz.todo.infrastructure.api.dto.ApiException;
import pl.kodz.todo.infrastructure.security.exception.DataProcessingException;
import pl.kodz.todo.infrastructure.security.exception.UserAlreadyExistsException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //TODO introduce RFC 7807 ProblemDetails
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiException> handleResponseStatus(ResponseStatusException ex) {
        ApiException error = new ApiException(ex.getStatusCode().value(), ex.getReason());
        return new ResponseEntity<>(error, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiException> handleAll(Exception ex) {
        ApiException error = new ApiException(INTERNAL_SERVER_ERROR.value(), "Unexpected error");
        ex.printStackTrace();
        return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    public ResponseEntity<ApiException> handleDatabaseConstraintError(
            org.hibernate.exception.ConstraintViolationException ex) {
        ApiException error = new ApiException(CONFLICT.value(), ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());
        return ResponseEntity.status(CONFLICT).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiException> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ApiException error = new ApiException(CONFLICT.value(), ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());
        return ResponseEntity.status(CONFLICT).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiException> handleUserExists(UserAlreadyExistsException ex) {
        ApiException error = new ApiException(CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(CONFLICT)
                .body(error);
    }

    @ExceptionHandler(DataProcessingException.class)
    public ResponseEntity<ApiException> handleDataError(DataProcessingException ex) {
        ApiException error = new ApiException(INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiException> handleDataError(EntityNotFoundException ex) {
        ApiException error = new ApiException(NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiException> handleDataError(BadCredentialsException ex) {
        ApiException error = new ApiException(UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.status(UNAUTHORIZED)
                .body(error);
    }
}
