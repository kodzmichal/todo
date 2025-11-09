package pl.kodz.todo.api.user;

import org.springframework.stereotype.Component;
import pl.kodz.todo.api.user.dto.CreateUserDto;
import pl.kodz.todo.api.user.dto.UpdateUserDto;
import pl.kodz.todo.api.user.dto.UserDtoResponse;
import pl.kodz.todo.domain.model.user.User;

@Component
public class UserApiMapper {

    public User updateDomain(CreateUserDto dto){
        User user = new User();
        user.setRole(dto.getRole());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        return user;
    }

    public UserDtoResponse toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDtoResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.isEnabled()
        );
    }

    public User updateDomain(User user, UpdateUserDto updateUserDto) {
        user.setEmail(updateUserDto.email());
        user.setFirstName(updateUserDto.firstName());
        user.setLastName(updateUserDto.lastName());

        return user;
    }
}
