package pl.kodz.todo.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
