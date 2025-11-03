package pl.kodz.todo.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kodz.todo.domain.model.user.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
}
