package com.backend.todo.exception;

import java.io.Serial;

public abstract class CommonException extends RuntimeException {
    public CommonException(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = -6333415982230536754L;
}
