package pl.kodz.todo.application.tasklist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kodz.todo.api.tasklist.dto.CreateTaskListDto;
import pl.kodz.todo.api.tasklist.dto.TaskListResponseDto;
import pl.kodz.todo.infrastructure.authentication.CustomUserDetails;

@Service
@RequiredArgsConstructor
public class TaskListService {
    public TaskListResponseDto createList(CustomUserDetails userDetails, CreateTaskListDto list) {
        TaskListResponseDto o = null;
        return o;
    }
}
