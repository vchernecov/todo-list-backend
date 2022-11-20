package com.backend.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class TodoTaskDto implements Serializable {
    private String title;
    private String body;
    private String state;
    private Long userId;
    private LocalDate dueDate;

    @Serial
    private static final long serialVersionUID = 5126105513949932615L;
}
