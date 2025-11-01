package pl.kodz.todo.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kodz.todo.api.IToDo;
import pl.kodz.todo.api.IUser;
import pl.kodz.todo.model.request.ToDoDto;
import pl.kodz.todo.model.response.UserDtoResponse;
import pl.kodz.todo.modeldata.User;
import pl.kodz.todo.repository.UserRepository;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController implements IUser {

    final UserRepository userRepository;

    @Override
    @GetMapping("/{userId}/users")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable Long userId) {

        Optional<User> user = userRepository.findById(userId);

        UserDtoResponse response = new UserDtoResponse();
        response.setId(user.get().getId());
        response.setEmail(user.get().getEmail());
        response.setFirstName(user.get().getFirstName());
        response.setLastName(user.get().getLastName());
        response.setEnabled(user.get().isEnabled());

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
