package pl.kodz.todo.api;

import org.springframework.http.ResponseEntity;
import pl.kodz.todo.model.request.UserDto;
import pl.kodz.todo.model.request.UserLogInDto;
import pl.kodz.todo.model.response.AuthResponse;

public interface IAuth {
    ResponseEntity<AuthResponse> register(UserDto user);
    ResponseEntity<AuthResponse> login(UserLogInDto user);
}
