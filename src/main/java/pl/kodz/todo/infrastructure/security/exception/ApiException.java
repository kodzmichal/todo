package pl.kodz.todo.infrastructure.security.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiException {
    private int status;
    private String message;
    private long timestamp;

    public ApiException(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}
