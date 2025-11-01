package pl.kodz.todo.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kodz.todo.api.IAuth;
import pl.kodz.todo.authentication.AuthenticationService;
import pl.kodz.todo.model.request.UserDto;
import pl.kodz.todo.model.response.AuthResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController  implements IAuth {
    private final AuthenticationService authService;

    @Override
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDto user) {
        return ResponseEntity.ok(authService.register(user));
    }
}
