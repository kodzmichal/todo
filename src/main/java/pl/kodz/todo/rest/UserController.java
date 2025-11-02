package pl.kodz.todo.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kodz.todo.api.IUser;
import pl.kodz.todo.model.response.UserDtoResponse;
import pl.kodz.todo.model.technical.CustomUserDetails;
import pl.kodz.todo.repository.UserRepository;
import pl.kodz.todo.user.UserService;

@RestController
@RequiredArgsConstructor
public class UserController implements IUser {

    final UserRepository userRepository;
    private final UserService userService;

    @Override
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable Long userId) {
        UserDtoResponse user = userService.obtainUserById(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Override
    @GetMapping("/users/me")
    public ResponseEntity<UserDtoResponse> getUserMe(@AuthenticationPrincipal CustomUserDetails userDetail) {
        UserDtoResponse user = userService.obtainUserById(userDetail.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Override
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> removeUserById(@PathVariable Long userId) {
        userService.removeUserById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
