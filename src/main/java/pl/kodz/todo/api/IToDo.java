package pl.kodz.todo.api;

import org.springframework.http.ResponseEntity;
import pl.kodz.todo.model.request.ToDoDto;

public interface IToDo {
    ResponseEntity<Void> createToDo(Long listId, ToDoDto todo);
}
