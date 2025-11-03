package pl.kodz.todo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kodz.todo.api.dto.request.CreateToDoListDto;
import pl.kodz.todo.infrastructure.authentication.CustomUserDetails;
import pl.kodz.todo.infrastructure.persistance.entity.ToDoList;
import pl.kodz.todo.repository.ToDoListRepository;

@RestController
@RequiredArgsConstructor
public class ToDoListController {

    private final ToDoListRepository toDoListRepository;

    @PostMapping("/lists")
    public ResponseEntity<Void> createList(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CreateToDoListDto list){
        System.out.println("Dodaję ToDoList do listy: " + userDetails.getUserId() + " -> " + list.getTitle());

        ToDoList toDoList = ToDoList.builder().
                title(list.getTitle())
                .creator(userDetails.getUserId() )
                .build();

        toDoListRepository.save(toDoList);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
