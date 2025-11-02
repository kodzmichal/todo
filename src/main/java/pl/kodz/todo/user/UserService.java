package pl.kodz.todo.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.kodz.todo.model.response.UserDtoResponse;
import pl.kodz.todo.modeldata.User;
import pl.kodz.todo.repository.UserRepository;
import pl.kodz.todo.user.mapper.UserDtoUserMapper;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoUserMapper userDtoUserMapper;

    public UserDtoResponse obtainUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userDtoUserMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));
    }


    public void removeUserById(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException ignored) {
            // do nothing — REST idempotent
        }
    }
}
