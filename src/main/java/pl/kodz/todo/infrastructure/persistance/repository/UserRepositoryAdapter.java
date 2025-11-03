package pl.kodz.todo.infrastructure.persistance.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kodz.todo.domain.model.user.User;
import pl.kodz.todo.domain.model.user.port.UserRepository;
import pl.kodz.todo.infrastructure.persistance.entity.UserEntity;
import pl.kodz.todo.infrastructure.persistance.mapper.UserMapper;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository{
    private final SpringDataUserRepository jpaRepo;
    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepo.findByEmail(email).map(UserMapper::toDomain);
    }

    @Override
    public void save(User user) {
        jpaRepo.save(UserMapper.toEntity(user));
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepo.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepo.deleteById(id);
    }
}
