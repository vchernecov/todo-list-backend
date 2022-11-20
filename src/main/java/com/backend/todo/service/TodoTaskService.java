package com.backend.todo.service;

import com.backend.todo.dto.TodoTaskDto;
import com.backend.todo.entity.TodoTaskEntity;
import com.backend.todo.entity.TodoTaskListEntity;
import com.backend.todo.entity.TodoTaskState;
import com.backend.todo.mapper.Mapper;
import com.backend.todo.repository.TodoTaskListRepository;
import com.backend.todo.repository.TodoTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TodoTaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoTaskService.class);
    private final TodoTaskListRepository todoTaskListRepository;
    private final Mapper<TodoTaskListEntity, TodoTaskDto> todoTaskListMapper;
    private final Mapper<List<TodoTaskDto>, TodoTaskListEntity> todoTaskMapper;
    private final Mapper<Set<TodoTaskEntity>, TodoTaskDto> todoTaskSetMapper;
    private final TodoTaskRepository todoTaskRepository;

    public TodoTaskService(
            TodoTaskListRepository todoTaskListRepository,
            Mapper<TodoTaskListEntity, TodoTaskDto> todoTaskListMapper,
            Mapper<List<TodoTaskDto>, TodoTaskListEntity> todoTaskMapper,
            Mapper<Set<TodoTaskEntity>, TodoTaskDto> todoTaskSetMapper,
            TodoTaskRepository todoTaskRepository
    ) {
        this.todoTaskListRepository = todoTaskListRepository;
        this.todoTaskListMapper = todoTaskListMapper;
        this.todoTaskMapper = todoTaskMapper;
        this.todoTaskSetMapper = todoTaskSetMapper;
        this.todoTaskRepository = todoTaskRepository;
    }

    @Transactional
    public void createTodoTask(TodoTaskDto todoTaskDto) {
        LOGGER.info("Start create task with parameters: {}^^", todoTaskDto);
        boolean isExistsTaskList = todoTaskListRepository.existsByUserId(todoTaskDto.getUserId());
        if (!isExistsTaskList) {
            LOGGER.info("New task will be created");
            TodoTaskListEntity todoTaskListEntity = todoTaskListMapper.map(todoTaskDto);
            todoTaskListRepository.save(todoTaskListEntity);
        } else {
            LOGGER.info("Task list with id: {} is exists", todoTaskDto.getUserId());
            TodoTaskListEntity todoTaskList = todoTaskListRepository.findByUserId(todoTaskDto.getUserId());
            Set<TodoTaskEntity> newTasks = todoTaskSetMapper.map(todoTaskDto);
            todoTaskList.mergeTasks(newTasks);
        }
    }

    public List<TodoTaskDto> getTodoTasks(long userId) {
        TodoTaskListEntity todoTaskListEntity = todoTaskListRepository.findByUserId(userId);

        return todoTaskMapper.map(todoTaskListEntity);
    }

    @Transactional
    public void changeStatus(long taskId, TodoTaskState todoTaskState) {
        Optional<TodoTaskEntity> task = todoTaskRepository.findById(taskId);

        task.ifPresentOrElse(entity -> {
                    entity.setState(todoTaskState);
                    LOGGER.info("Task with id {} removed", entity.getId());
                },
                () -> {
                    LOGGER.error("Task with id: {} not found", taskId);
                }
        );
    }
}
