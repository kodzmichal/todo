package pl.kodz.todo.api;

import org.springframework.http.ResponseEntity;
import pl.kodz.todo.model.response.UserDtoResponse;

public interface IUser {
    ResponseEntity<UserDtoResponse> getUserById(Long id);
}
