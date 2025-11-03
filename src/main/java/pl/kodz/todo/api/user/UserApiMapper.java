package pl.kodz.todo.api.user;

import pl.kodz.todo.api.dto.request.UserDto;
import pl.kodz.todo.domain.model.user.User;

public  class UserApiMapper {

    public static User toDomain (UserDto dto){
        User user = new User();
        user.setRole(dto.getRole());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        return user;
    }
}
