package pl.kodz.todo.api.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToDoDto {
    private String title;
    private String description;
}
