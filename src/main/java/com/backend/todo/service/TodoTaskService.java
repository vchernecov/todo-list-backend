package com.backend.todo.service;

import com.backend.todo.dto.TodoTaskDto;
import com.backend.todo.entity.TodoTaskListEntity;
import com.backend.todo.mapper.Mapper;
import com.backend.todo.repository.TodoTaskListRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoTaskService {
    private final TodoTaskListRepository todoTaskListRepository;
    private final Mapper<TodoTaskListEntity, TodoTaskDto> todoTaskListMapper;

    public TodoTaskService(TodoTaskListRepository todoTaskListRepository, Mapper<TodoTaskListEntity, TodoTaskDto> todoTaskListMapper) {
        this.todoTaskListRepository = todoTaskListRepository;
        this.todoTaskListMapper = todoTaskListMapper;
    }

    public void createTodoTask(TodoTaskDto todoTaskDto) {
        TodoTaskListEntity todoTaskListEntity = todoTaskListMapper.map(todoTaskDto);
        try {
            todoTaskListRepository.save(todoTaskListEntity);
        } catch (Exception e) {
            System.out.println("error"); // TODO Logger
        }
    }
}
