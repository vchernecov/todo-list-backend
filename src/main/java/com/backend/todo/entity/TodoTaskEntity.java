package com.backend.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
