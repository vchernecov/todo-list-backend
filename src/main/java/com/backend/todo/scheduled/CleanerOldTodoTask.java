package com.backend.todo.scheduled;

import com.backend.todo.service.TodoTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CleanerOldTodoTask {
    private final TodoTaskService todoTaskService;

    public CleanerOldTodoTask(TodoTaskService todoTaskService) {
        this.todoTaskService = todoTaskService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CleanerOldTodoTask.class);

    @Scheduled(cron = "* * * * * *")
    public void cleanDateExpiredTasks() {
        todoTaskService.removeOldTodoTasks();
    }
}
