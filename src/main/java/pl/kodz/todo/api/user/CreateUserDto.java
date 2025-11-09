package pl.kodz.todo.api.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kodz.todo.domain.model.user.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String password;
    private Role role;
}
