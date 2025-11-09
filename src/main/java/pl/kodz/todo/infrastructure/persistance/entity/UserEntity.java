package pl.kodz.todo.infrastructure.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.kodz.todo.domain.model.user.Role;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;
    @Enumerated(EnumType.STRING)
    private Role role;

    //TODO better annotations
    @CreationTimestamp
    private LocalDateTime createdAt;

    //TODO better annotations
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
