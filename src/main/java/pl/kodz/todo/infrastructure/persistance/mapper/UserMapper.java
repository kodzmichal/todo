package pl.kodz.todo.infrastructure.persistance.mapper;

import pl.kodz.todo.domain.model.user.User;
import pl.kodz.todo.infrastructure.persistance.entity.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity entity){
        User domainUser = new User();
        domainUser.setId(entity.getId());
        domainUser.setRole(entity.getRole());
        domainUser.setFirstName(entity.getFirstName());
        domainUser.setEmail(entity.getEmail());
        domainUser.setLastName(entity.getLastName());
        domainUser.setEnabled(entity.isEnabled());
        return domainUser;
    }

    public static UserEntity toEntity(User domain){
        UserEntity entity = new UserEntity();
        entity.setEmail(domain.getEmail());
        entity.setRole(domain.getRole());
        entity.setPassword(domain.getPassword());
        entity.setFirstName(domain.getFirstName());
        entity.setLastName(domain.getLastName());
        entity.setEnabled(domain.isEnabled());
        entity.setId(domain.getId());
        return entity;
    }

}
