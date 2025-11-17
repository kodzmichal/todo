package pl.kodz.todo.api.tasklist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTaskListDto {
    private String title;
}
