package com.backend.todo.controller;

import com.backend.todo.dto.TodoTaskDto;
import com.backend.todo.service.TodoTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    private final TodoTaskService todoTaskService;

    public TodoController(TodoTaskService todoTaskService) {
        this.todoTaskService = todoTaskService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody TodoTaskDto todoTaskDto) {
        todoTaskService.createTodoTask(todoTaskDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
