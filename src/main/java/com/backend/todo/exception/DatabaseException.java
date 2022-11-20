package com.backend.todo.exception;

import java.io.Serial;

public class DatabaseException extends CommonException {
    @Serial
    private static final long serialVersionUID = 3929436290425104597L;

    public DatabaseException(String message) {
        super(message);
    }
}
