package com.backend.todo.service;

import com.backend.todo.dto.TodoTaskDto;
import com.backend.todo.entity.TodoTaskListEntity;
import com.backend.todo.mapper.Mapper;
import com.backend.todo.repository.TodoTaskListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoTaskService {
    private final TodoTaskListRepository todoTaskListRepository;
    private final Mapper<TodoTaskListEntity, TodoTaskDto> todoTaskListMapper;
    private final Mapper<List<TodoTaskDto>, TodoTaskListEntity> todoTaskMapper;

    public TodoTaskService(
            TodoTaskListRepository todoTaskListRepository,
            Mapper<TodoTaskListEntity, TodoTaskDto> todoTaskListMapper,
            Mapper<List<TodoTaskDto>, TodoTaskListEntity> todoTaskMapper
    ) {
        this.todoTaskListRepository = todoTaskListRepository;
        this.todoTaskListMapper = todoTaskListMapper;
        this.todoTaskMapper = todoTaskMapper;
    }

    public void createTodoTask(TodoTaskDto todoTaskDto) {
        TodoTaskListEntity todoTaskListEntity = todoTaskListMapper.map(todoTaskDto);
        try {
            todoTaskListRepository.save(todoTaskListEntity);
        } catch (Exception e) {
            System.out.println("error"); // TODO Logger
        }
    }

    public List<TodoTaskDto> getTodoTasks(long userId) {
        TodoTaskListEntity todoTaskListEntity = todoTaskListRepository.findByUserId(userId);

        return todoTaskMapper.map(todoTaskListEntity);
    }
}
