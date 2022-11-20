package com.backend.todo.exception;

public class TaskNotFoundException extends CommonException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
