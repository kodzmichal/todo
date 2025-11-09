package pl.kodz.todo.api.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kodz.todo.api.user.UserApiMapper;
import pl.kodz.todo.application.auth.AuthenticationService;
import pl.kodz.todo.api.user.dto.CreateUserDto;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody CreateUserDto user) {
        return ResponseEntity.ok(authService.register(UserApiMapper.updateDomain(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody UserLogInDto user) {
        return ResponseEntity.ok(authService.login(user));
    }
}
