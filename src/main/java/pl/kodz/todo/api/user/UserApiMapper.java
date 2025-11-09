package pl.kodz.todo.api.user;

import pl.kodz.todo.domain.model.user.User;

public  class UserApiMapper {

    public static User updateDomain(CreateUserDto dto){
        User user = new User();
        user.setRole(dto.getRole());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        return user;
    }

    public UserDtoResponse toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDtoResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.isEnabled()
        );
    }

    public User updateDomain(User user, UpdateUserDto updateUserDto) {
        user.setEmail(updateUserDto.email());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());

        return user;
    }
}
