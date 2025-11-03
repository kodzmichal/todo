package pl.kodz.todo.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kodz.todo.domain.model.user.port.UserRepository;
import pl.kodz.todo.infrastructure.authentication.CustomUserDetails;
import pl.kodz.todo.application.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable Long userId) {
        UserDtoResponse user = userService.obtainUserById(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/users/me")
    public ResponseEntity<UserDtoResponse> getUserMe(@AuthenticationPrincipal CustomUserDetails userDetail) {
        UserDtoResponse user = userService.obtainUserById(userDetail.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> removeUserById(@PathVariable Long userId) {
        userService.removeUserById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
