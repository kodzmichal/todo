package pl.kodz.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kodz.todo.infrastructure.persistance.entity.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {}