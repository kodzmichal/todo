package pl.kodz.todo.api.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.kodz.todo.api.user.dto.ChangePasswordDto;
import pl.kodz.todo.api.user.dto.UserDtoResponse;
import pl.kodz.todo.application.user.UserService;

@RestController
@RequestMapping("/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserAdminController {
    private final UserService userService;
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable Long userId) {
        UserDtoResponse user = userService.findUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PatchMapping("/users/{userId}/password")
    public ResponseEntity<Void> changePassword(@PathVariable Long userId, @Valid @RequestBody ChangePasswordDto passwordDto){
        userService.changePassword(userId, passwordDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //TODO enable/disable User
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> removeUserById(@PathVariable Long userId) {
        userService.removeUserById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
