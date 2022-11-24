package com.backend.todo.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "todo_task")
@Entity
@SequenceGenerator(
        name = "todo_task_generator",
        sequenceName = "todo_task_sequence",
        allocationSize = 1
)
public class TodoTaskEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -398084062877235764L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_task_generator")
    private Long id;
    private String title;
    private String body;
    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private TodoTaskState state;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TodoTaskEntity that = (TodoTaskEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
