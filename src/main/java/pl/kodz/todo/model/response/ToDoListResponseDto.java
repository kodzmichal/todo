package pl.kodz.todo.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kodz.todo.model.request.ToDoDto;

import java.util.Set;

@Data
@NoArgsConstructor
public class ToDoListResponseDto {
    private Long id;
    private String title;
    private Set<ToDoDto> toDos;
}
