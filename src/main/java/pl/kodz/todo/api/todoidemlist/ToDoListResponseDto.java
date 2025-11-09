package pl.kodz.todo.api.todoidemlist;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kodz.todo.api.todoitem.ToDoDto;

import java.util.Set;

@Data
@NoArgsConstructor
public class ToDoListResponseDto {
    private Long id;
    private String title;
    private Set<ToDoDto> toDos;
}
