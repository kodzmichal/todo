package pl.kodz.todo.application.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kodz.todo.api.user.ChangePasswordDto;
import pl.kodz.todo.api.user.UpdateUserDto;
import pl.kodz.todo.api.user.UserApiMapper;
import pl.kodz.todo.api.user.UserDtoResponse;
import pl.kodz.todo.domain.model.user.User;
import pl.kodz.todo.domain.model.user.port.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserApiMapper userApiMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDtoResponse findUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userApiMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));
    }


    public void removeUserById(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException ignored) {
            // do nothing — REST idempotent
        }
    }

    public void changePassword(Long userId, ChangePasswordDto passwordDto) {
        User user = getUserById(userId);

        if (!passwordEncoder.matches(passwordDto.oldPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect old password");
        }

        user.setPassword(passwordEncoder.encode(passwordDto.newPassword()));
        userRepository.save(user);
    }

    public UserDtoResponse updateUser(Long userId, UpdateUserDto updateUserDto) {
        User user = getUserById(userId);

        User updatedUser = userApiMapper.updateDomain(user, updateUserDto);

        userRepository.save(updatedUser);

        return userApiMapper.toDto(updatedUser);
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
