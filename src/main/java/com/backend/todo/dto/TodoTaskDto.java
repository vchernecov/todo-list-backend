package com.backend.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TodoTaskDto {
    private String title;
    private String body;
    private Long userId;
    private LocalDate dueDate;
}
