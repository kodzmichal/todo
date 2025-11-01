package pl.kodz.todo.api;

import org.springframework.http.ResponseEntity;
import pl.kodz.todo.model.request.CreateToDoListDto;

public interface IToDoList {
    ResponseEntity<Void> createList(Long creator, CreateToDoListDto list);
}
