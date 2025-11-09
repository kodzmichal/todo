package pl.kodz.todo.infrastructure.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiException {
    //TODO make it standard exception
    private int status;
    private String message;
    private long timestamp;

    public ApiException(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}
