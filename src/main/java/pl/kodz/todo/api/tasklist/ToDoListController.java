package pl.kodz.todo.api.tasklist;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kodz.todo.api.tasklist.dto.CreateTaskListDto;
import pl.kodz.todo.api.tasklist.dto.TaskListResponseDto;
import pl.kodz.todo.application.tasklist.TaskListService;
import pl.kodz.todo.infrastructure.authentication.CustomUserDetails;

@RestController
@RequiredArgsConstructor
public class ToDoListController {

    private final TaskListService taskListService;

    @PostMapping("/todolists")
    public ResponseEntity<TaskListResponseDto> createList(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CreateTaskListDto list){

        taskListService.createList(userDetails, list);

//        ToDoList toDoList = ToDoList.builder().
//                title(list.getTitle())
//                .creator(userDetails.getUserId() )
//                .build();


        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
