package pl.kodz.todo.api.user.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordDto(@NotBlank String newPassword, String oldPassword) {}
