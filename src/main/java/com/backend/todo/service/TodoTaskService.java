package com.backend.todo.service;

import com.backend.todo.dto.TodoTaskDto;
import com.backend.todo.entity.TodoTaskEntity;
import com.backend.todo.entity.TodoTaskListEntity;
import com.backend.todo.mapper.Mapper;
import com.backend.todo.repository.TodoTaskListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class TodoTaskService {
    private final TodoTaskListRepository todoTaskListRepository;
    private final Mapper<TodoTaskListEntity, TodoTaskDto> todoTaskListMapper;
    private final Mapper<List<TodoTaskDto>, TodoTaskListEntity> todoTaskMapper;
    private final Mapper<Set<TodoTaskEntity>, TodoTaskDto> todoTaskSetMapper;

    public TodoTaskService(
            TodoTaskListRepository todoTaskListRepository,
            Mapper<TodoTaskListEntity, TodoTaskDto> todoTaskListMapper,
            Mapper<List<TodoTaskDto>, TodoTaskListEntity> todoTaskMapper,
            Mapper<Set<TodoTaskEntity>, TodoTaskDto> todoTaskSetMapper
    ) {
        this.todoTaskListRepository = todoTaskListRepository;
        this.todoTaskListMapper = todoTaskListMapper;
        this.todoTaskMapper = todoTaskMapper;
        this.todoTaskSetMapper = todoTaskSetMapper;
    }

    @Transactional
    public void createTodoTask(TodoTaskDto todoTaskDto) {
        TodoTaskListEntity todoTaskListEntity = todoTaskListMapper.map(todoTaskDto);
        boolean isExistsTaskList = todoTaskListRepository.existsByUserId(todoTaskDto.getUserId());
        if (!isExistsTaskList) {
            todoTaskListRepository.save(todoTaskListEntity);
        } else {
            TodoTaskListEntity todoTaskList = todoTaskListRepository.findByUserId(todoTaskDto.getUserId());
            Set<TodoTaskEntity> todoTasks = todoTaskSetMapper.map(todoTaskDto);
            todoTaskList.mergeTasks(todoTasks);
        }
    }

    public List<TodoTaskDto> getTodoTasks(long userId) {
        TodoTaskListEntity todoTaskListEntity = todoTaskListRepository.findByUserId(userId);

        return todoTaskMapper.map(todoTaskListEntity);
    }
}
