package pl.kodz.todo.infrastructure.persistance.entity;

import jakarta.persistence.*;

@Entity
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "task_list_id")
    private TaskListEntity list;
}