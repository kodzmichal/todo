package pl.kodz.todo.api.auth.dto;

import jakarta.validation.constraints.NotBlank;


public record UserLogInDto (
    @NotBlank
     String email,
    @NotBlank
     String password
){}
