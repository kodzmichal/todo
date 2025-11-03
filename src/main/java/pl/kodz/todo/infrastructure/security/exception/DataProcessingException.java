package pl.kodz.todo.infrastructure.security.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException( Throwable cause) {
        super("Exception during save action", cause);
    }
}