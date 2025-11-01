package pl.kodz.todo.api;

import org.springframework.http.ResponseEntity;
import pl.kodz.todo.model.request.CreateToDoListDto;
import pl.kodz.todo.model.technical.CustomUserDetails;

public interface IToDoList {
    ResponseEntity<Void> createList(CustomUserDetails userDetails, CreateToDoListDto list);
}
