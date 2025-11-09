package pl.kodz.todo.api.auth;

import jakarta.validation.constraints.NotBlank;


public record UserLogInDto (
    @NotBlank
     String email,
    @NotBlank
     String password
){}
