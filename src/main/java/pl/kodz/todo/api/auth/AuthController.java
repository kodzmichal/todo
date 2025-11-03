package pl.kodz.todo.api.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kodz.todo.api.user.UserApiMapper;
import pl.kodz.todo.infrastructure.authentication.AuthenticationService;
import pl.kodz.todo.api.dto.request.UserDto;
import pl.kodz.todo.api.user.UserLogInDto;
import pl.kodz.todo.api.dto.response.AuthResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDto user) {
        return ResponseEntity.ok(authService.register(UserApiMapper.toDomain(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(UserLogInDto user) {
        return ResponseEntity.ok(authService.login(user));
    }
}
