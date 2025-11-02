package pl.kodz.todo.user.mapper;

import org.springframework.stereotype.Component;
import pl.kodz.todo.model.response.UserDtoResponse;
import pl.kodz.todo.modeldata.User;

@Component
public class UserDtoUserMapper {

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
}
