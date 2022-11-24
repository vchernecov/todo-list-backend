package com.backend.todo.entity;

import com.backend.todo.utils.CollectionHelper;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "todo_task_list")
@SequenceGenerator(
        name = "todo_task_list_generator",
        sequenceName = "todo_task_list_sequence",
        allocationSize = 1
)
public class TodoTaskListEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -8811781968704991368L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_task_list_generator")
    private Long id;

    private Long userId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "todo_task_list_id")
    @Builder.Default
    private Set<TodoTaskEntity> tasks = Collections.emptySet();

    public void mergeTasks(Set<TodoTaskEntity> newTasks) {
        setTasks(CollectionHelper.merge(tasks, newTasks));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TodoTaskListEntity that = (TodoTaskListEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
