package pl.kodz.todo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kodz.todo.modeldata.Role;

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
