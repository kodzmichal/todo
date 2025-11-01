package pl.kodz.todo.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateToDoListDto {
    private String creator;
    private String title;
}
