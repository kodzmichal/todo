package pl.kodz.todo.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import pl.kodz.todo.model.technical.ApiException;
import pl.kodz.todo.model.technical.exception.DataProcessingException;
import pl.kodz.todo.model.technical.exception.UserAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiException> handleResponseStatus(ResponseStatusException ex) {
        ApiException error = new ApiException(ex.getStatusCode().value(), ex.getReason());
        return new ResponseEntity<>(error, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiException> handleAll(Exception ex) {
        ApiException error = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unexpected error");
        ex.printStackTrace();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    public ResponseEntity<ApiException> handleDatabaseConstraintError(
            org.hibernate.exception.ConstraintViolationException ex) {
        ApiException error = new ApiException(HttpStatus.CONFLICT.value(), ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiException> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ApiException error = new ApiException(HttpStatus.CONFLICT.value(), ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiException> handleUserExists(UserAlreadyExistsException ex) {
        ApiException error = new ApiException(HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error);
    }

    @ExceptionHandler(DataProcessingException.class)
    public ResponseEntity<ApiException> handleDataError(DataProcessingException ex) {
        ApiException error = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
