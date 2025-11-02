package pl.kodz.todo.model.response;

public record UserDtoResponse(Long id, String email, String firstName, String lastName, boolean enabled) {}

