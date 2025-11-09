package pl.kodz.todo.api.todoidemlist;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateToDoListDto {
    private String creator;
    private String title;
}
