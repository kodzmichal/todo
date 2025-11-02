package pl.kodz.todo.rest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.kodz.todo.api.IToDo;
import pl.kodz.todo.api.IUser;
import pl.kodz.todo.model.request.ToDoDto;
import pl.kodz.todo.model.response.UserDtoResponse;
import pl.kodz.todo.model.technical.CustomUserDetails;
import pl.kodz.todo.modeldata.User;
import pl.kodz.todo.repository.UserRepository;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController implements IUser {

    final UserRepository userRepository;

    @Override
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));

        UserDtoResponse response = new UserDtoResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEnabled(user.isEnabled());

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @GetMapping("/users/me")
   public ResponseEntity<UserDtoResponse> getUserMe(@AuthenticationPrincipal CustomUserDetails userDetail) {

        User user = userRepository.findById(userDetail.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userDetail.getUserId()));

        UserDtoResponse response = new UserDtoResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEnabled(user.isEnabled());

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
