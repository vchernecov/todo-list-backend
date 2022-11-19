package com.backend.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo_task_list")
@SequenceGenerator(
        name = "todo_task_list_generator",
        sequenceName = "todo_task_list_sequence",
        allocationSize = 1
)
public class TodoTaskListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_task_list_generator")
    private Long id;

    private Long userId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "todo_task_list_id")
    private Set<TodoTaskEntity> tasks = Collections.emptySet();
}
