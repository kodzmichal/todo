package pl.kodz.todo.api.tasklist;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kodz.todo.api.tasklist.dto.CreateTaskListDto;
import pl.kodz.todo.api.tasklist.dto.TaskListResponseDto;
import pl.kodz.todo.application.tasklist.TaskListService;
import pl.kodz.todo.infrastructure.authentication.CustomUserDetails;

@RestController
@RequestMapping("/v1/todolist")
@RequiredArgsConstructor
public class ToDoListController {

    private final TaskListService taskListService;

    @PostMapping("/create")
    public ResponseEntity<TaskListResponseDto> createList(@AuthenticationPrincipal CustomUserDetails userDetails, @Valid  @RequestBody CreateTaskListDto list){

        taskListService.createList(userDetails, list);

//        ToDoList toDoList = ToDoList.builder().
//                title(list.getTitle())
//                .creator(userDetails.getUserId() )
//                .build();


        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
