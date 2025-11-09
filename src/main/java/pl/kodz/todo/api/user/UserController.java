package pl.kodz.todo.api.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.kodz.todo.api.user.dto.ChangePasswordDto;
import pl.kodz.todo.api.user.dto.UpdateUserDto;
import pl.kodz.todo.api.user.dto.UserDtoResponse;
import pl.kodz.todo.infrastructure.authentication.CustomUserDetails;
import pl.kodz.todo.application.user.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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

    @PutMapping("/users/me")
    public ResponseEntity<UserDtoResponse> updateUser(@AuthenticationPrincipal CustomUserDetails userDetail, @Valid @RequestBody UpdateUserDto updateUserDto){
        UserDtoResponse updatedUser = userService.updateUser(userDetail.getUserId(), updateUserDto);
        return ResponseEntity.ok(updatedUser);
    }
}
