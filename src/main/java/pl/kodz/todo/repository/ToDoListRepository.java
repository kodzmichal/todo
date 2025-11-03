package pl.kodz.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kodz.todo.infrastructure.persistance.entity.ToDoList;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {}
