package pl.kodz.todo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kodz.todo.api.dto.request.ToDoDto;

@RestController
public class ToDoController {
    @PostMapping("/{listId}/todos")
    public ResponseEntity<Void> createToDo(@PathVariable Long listId, @RequestBody ToDoDto todo) {

        System.out.println("Dodaję ToDo do listy: " + listId + " -> " + todo.getTitle());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
