package pl.kodz.todo.api.todoitem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDto {
    private String title;
    private String description;
}
