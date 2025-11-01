package pl.kodz.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kodz.todo.modeldata.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
