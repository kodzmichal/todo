package pl.kodz.todo.infrastructure.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kodz.todo.infrastructure.persistance.entity.UserEntity;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
