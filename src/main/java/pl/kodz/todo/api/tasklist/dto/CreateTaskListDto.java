package pl.kodz.todo.api.tasklist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTaskListDto {
    @NotBlank
    private String title;
}
