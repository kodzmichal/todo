package pl.kodz.todo.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kodz.todo.api.IToDoList;
import pl.kodz.todo.model.request.CreateToDoListDto;
import pl.kodz.todo.model.technical.CustomUserDetails;
import pl.kodz.todo.modeldata.ToDoList;
import pl.kodz.todo.repository.ToDoListRepository;

@RestController
@RequiredArgsConstructor
public class ToDoListController implements IToDoList {

    private final ToDoListRepository toDoListRepository;

    @Override
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
