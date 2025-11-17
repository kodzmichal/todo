package pl.kodz.todo.domain.model.tasklist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kodz.todo.domain.model.task.Task;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskList {
    private Long id;
    private String title;
    private Set<Task> tasks;
}
