package pl.kodz.todo.api.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.kodz.todo.infrastructure.authentication.CustomUserDetails;
import pl.kodz.todo.application.user.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable Long userId) {
        UserDtoResponse user = userService.findUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/users/me")
    public ResponseEntity<UserDtoResponse> getUserMe(@AuthenticationPrincipal CustomUserDetails userDetail) {
        UserDtoResponse user = userService.findUserById(userDetail.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PatchMapping("/users/me/password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordDto passwordDto, @AuthenticationPrincipal CustomUserDetails userDetail){
        userService.changePassword(userDetail.getUserId(), passwordDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/admin/users/{userId}/password")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> changePassword(@PathVariable Long userId, @Valid @RequestBody ChangePasswordDto passwordDto){
        userService.changePassword(userId, passwordDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/users/me")
    public ResponseEntity<UserDtoResponse> updateUser(@AuthenticationPrincipal CustomUserDetails userDetail, @Valid @RequestBody UpdateUserDto updateUserDto){
        UserDtoResponse updatedUser = userService.updateUser(userDetail.getUserId(), updateUserDto);
        return ResponseEntity.ok(updatedUser);
    }

    //TODO enable/disable User

    @DeleteMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> removeUserById(@PathVariable Long userId) {
        userService.removeUserById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
