package com.backend.todo.mapper;

import com.backend.todo.dto.TodoTaskDto;
import com.backend.todo.entity.TodoTaskEntity;
import com.backend.todo.entity.TodoTaskListEntity;
import com.backend.todo.entity.TodoTaskState;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Component
public class TodoTaskListMapper implements Mapper<TodoTaskListEntity, TodoTaskDto> {
    @Override
    public TodoTaskListEntity map(TodoTaskDto source) {
        return TodoTaskListEntity.builder()
                .userId(source.getUserId())
                .tasks(toTodoTaskEntity(source))
                .build();
    }

    private Set<TodoTaskEntity> toTodoTaskEntity(TodoTaskDto todoTaskDto) {
        return Collections.singleton(TodoTaskEntity.builder()
                .title(todoTaskDto.getTitle())
                .body(todoTaskDto.getBody())
                .dueDate(todoTaskDto.getDueDate())
                .state(TodoTaskState.IN_PROGRESS)
                .createDateTime(LocalDateTime.now())
                .updateDateTime(null)
                .build()
        );
    }
}
