package com.backend.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class TodoTaskDto {
    private String title;
    private String body;
    private Long userId;
    private LocalDate dueDate;
}
