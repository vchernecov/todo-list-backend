package com.backend.todo.mapper;

import com.backend.todo.dto.TodoTaskDto;
import com.backend.todo.entity.TodoTaskEntity;
import com.backend.todo.entity.TodoTaskState;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class TodoTaskSetMapper implements Mapper<Set<TodoTaskEntity>, TodoTaskDto> {
    @Override
    public Set<TodoTaskEntity> map(TodoTaskDto source) {
        return Set.of(TodoTaskEntity.builder()
                .title(source.getTitle())
                .body(source.getBody())
                .dueDate(source.getDueDate())
                .state(source.getState() != null ? TodoTaskState.valueOf(source.getState()) : TodoTaskState.IN_PROGRESS)
                .createDateTime(LocalDateTime.now())
                .build());
    }
}
