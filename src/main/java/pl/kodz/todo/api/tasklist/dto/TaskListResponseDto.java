package pl.kodz.todo.api.tasklist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kodz.todo.api.todoitem.TaskDto;

import java.util.Set;

@Data
@NoArgsConstructor
public class TaskListResponseDto {
    private Long id;
    private String title;
    private Set<TaskDto> toDos;
}
