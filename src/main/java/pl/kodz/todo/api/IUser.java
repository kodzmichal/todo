package pl.kodz.todo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import pl.kodz.todo.model.response.UserDtoResponse;
import pl.kodz.todo.model.technical.CustomUserDetails;

public interface IUser {
    ResponseEntity<UserDtoResponse> getUserById(Long id);
    ResponseEntity<UserDtoResponse> getUserMe(CustomUserDetails userDetails);
    ResponseEntity<Void> removeUserById(Long id);
}
