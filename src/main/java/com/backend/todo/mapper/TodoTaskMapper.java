package com.backend.todo.mapper;

import com.backend.todo.dto.TodoTaskDto;
import com.backend.todo.entity.TodoTaskListEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoTaskMapper implements Mapper<List<TodoTaskDto>, TodoTaskListEntity> {
    @Override
    public List<TodoTaskDto> map(TodoTaskListEntity source) {
        return source.getTasks()
                .stream()
                .map(entity -> TodoTaskDto.builder()
                        .userId(source.getUserId())
                        .title(entity.getTitle())
                        .body(entity.getBody())
                        .dueDate(entity.getDueDate())
                        .build())
                .collect(Collectors.toList());
    }
}
