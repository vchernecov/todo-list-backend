package com.backend.todo.scheduled;

import com.backend.todo.entity.TodoTaskEntity;
import com.backend.todo.service.TodoTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CleanerOldTodoTask {
    private final TodoTaskService todoTaskService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CleanerOldTodoTask.class);
    public CleanerOldTodoTask(TodoTaskService todoTaskService) {
        this.todoTaskService = todoTaskService;
    }

    @Scheduled(cron = "0 0 0/10 ? * * ")
    public void cleanDateExpiredTasks() {
        List<TodoTaskEntity> removedTodoTasks = todoTaskService.removeOldTodoTasks();

        LOGGER.info("Removed {} expired tasks", removedTodoTasks.size());
    }
}
