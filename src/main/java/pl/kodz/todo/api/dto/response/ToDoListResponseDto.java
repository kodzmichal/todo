package pl.kodz.todo.api.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kodz.todo.api.dto.request.ToDoDto;

import java.util.Set;

@Data
@NoArgsConstructor
public class ToDoListResponseDto {
    private Long id;
    private String title;
    private Set<ToDoDto> toDos;
}
