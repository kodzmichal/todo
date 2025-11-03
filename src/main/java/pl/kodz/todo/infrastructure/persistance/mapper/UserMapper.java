package pl.kodz.todo.infrastructure.persistance.mapper;

import pl.kodz.todo.domain.model.user.User;
import pl.kodz.todo.infrastructure.persistance.entity.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity entity){
        return null;
    }

    public static UserEntity toEntity(User domain){
        UserEntity entity = new UserEntity();
        entity.setEnabled(domain.isEnabled());
        entity.setEmail(domain.getEmail());
        entity.setRole(domain.getRole());
        entity.setPassword(domain.getPassword());
        entity.setFirstName(domain.getFirstName());
        entity.setLastName(domain.getLastName());
        entity.setEnabled(domain.isEnabled());
        return entity;
    }

}
