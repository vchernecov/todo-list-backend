package com.backend.todo.service;

import com.backend.todo.dto.TodoTaskDto;
import com.backend.todo.entity.TodoTaskEntity;
import com.backend.todo.entity.TodoTaskListEntity;
import com.backend.todo.entity.TodoTaskState;
import com.backend.todo.exception.DatabaseException;
import com.backend.todo.exception.TaskNotFoundException;
import com.backend.todo.mapper.Mapper;
import com.backend.todo.repository.TodoTaskListRepository;
import com.backend.todo.repository.TodoTaskRepository;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TodoTaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoTaskService.class);
    private final TodoTaskListRepository todoTaskListRepository;
    private final Mapper<TodoTaskListEntity, TodoTaskDto> todoTaskListMapper;
    private final Mapper<List<TodoTaskDto>, TodoTaskListEntity> todoTaskMapper;
    private final Mapper<Set<TodoTaskEntity>, TodoTaskDto> todoTaskSetMapper;
    private final TodoTaskRepository todoTaskRepository;

    public TodoTaskService(
            final TodoTaskListRepository todoTaskListRepository,
            final Mapper<TodoTaskListEntity, TodoTaskDto> todoTaskListMapper,
            final Mapper<List<TodoTaskDto>, TodoTaskListEntity> todoTaskMapper,
            final Mapper<Set<TodoTaskEntity>, TodoTaskDto> todoTaskSetMapper,
            final TodoTaskRepository todoTaskRepository
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
        try {
            boolean isExistsTaskList = todoTaskListRepository.existsByUserId(todoTaskDto.getUserId());
            if (!isExistsTaskList) {
                LOGGER.info("New task will be created");
                TodoTaskListEntity todoTaskListEntity = todoTaskListMapper.map(todoTaskDto);
                todoTaskListRepository.save(todoTaskListEntity);
            } else {
                LOGGER.info("Task list with id: {} is exists", todoTaskDto.getUserId());
                TodoTaskListEntity todoTaskList = todoTaskListRepository
                        .findByUserId(todoTaskDto.getUserId());
                Set<TodoTaskEntity> newTasks = todoTaskSetMapper.map(todoTaskDto);
                todoTaskList.mergeTasks(newTasks);
            }
        } catch (HibernateException e) {
            LOGGER.error("Database error: {}", e.getMessage());

            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public List<TodoTaskDto> getTodoTasks(long userId) {
        TodoTaskListEntity todoTaskListEntity = todoTaskListRepository
                .findByUserId(userId);

        return todoTaskMapper.map(todoTaskListEntity);
    }

    @Transactional
    public void changeStatus(long taskId, TodoTaskState todoTaskState) {
        Optional<TodoTaskEntity> task = todoTaskRepository.findById(taskId);

        task.ifPresentOrElse(entity -> {
                    entity.setState(todoTaskState);
                    entity.setUpdateDateTime(LocalDateTime.now());
                    LOGGER.info("Task with id {} removed", entity.getId());
                },
                () -> {
                    LOGGER.error("Task with id: {} not found", taskId);

                    throw new TaskNotFoundException("Task not found");
                }
        );
    }

    @Transactional
    public List<TodoTaskEntity> removeOldTodoTasks() {
        List<TodoTaskEntity> todoTaskEntities = todoTaskRepository.findByDueDateBeforeAndStateNot(LocalDate.now(), TodoTaskState.ARCHIVE);

        return todoTaskEntities
                .stream()
                .peek(todoTask -> {
                    todoTask.setState(TodoTaskState.ARCHIVE);
                })
                .collect(Collectors.toList());
    }
}
