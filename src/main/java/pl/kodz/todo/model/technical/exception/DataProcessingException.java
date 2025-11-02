package pl.kodz.todo.model.technical.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException( Throwable cause) {
        super("Exception during save action", cause);
    }
}