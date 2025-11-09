package pl.kodz.todo.infrastructure.security.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String field) {
        super("User already exist with corresponding - " + field);
    }
}