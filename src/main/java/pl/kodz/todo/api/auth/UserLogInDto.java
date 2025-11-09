package pl.kodz.todo.api.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record UserLogInDto (
    @NotBlank
     String email,
    @NotBlank
     String password
){}
