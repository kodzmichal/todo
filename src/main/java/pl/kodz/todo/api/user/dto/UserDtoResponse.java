package pl.kodz.todo.api.user.dto;

public record UserDtoResponse(Long id, String email, String firstName, String lastName, boolean enabled) {}

