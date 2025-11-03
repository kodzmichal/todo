package pl.kodz.todo.infrastructure.security.exception;

import lombok.Data;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String field) {
        super("User already exist with coresponding - " + field);
    }
}