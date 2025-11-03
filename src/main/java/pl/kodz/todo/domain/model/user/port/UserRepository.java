package pl.kodz.todo.domain.model.user.port;

import pl.kodz.todo.domain.model.user.User;
import pl.kodz.todo.infrastructure.persistance.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    void save(User user);
    Optional<User> findById(Long id);
    void deleteById(Long id);
}
