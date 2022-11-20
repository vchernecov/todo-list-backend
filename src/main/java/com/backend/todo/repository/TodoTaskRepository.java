package com.backend.todo.repository;

import com.backend.todo.entity.TodoTaskEntity;
import com.backend.todo.entity.TodoTaskState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoTaskRepository extends JpaRepository<TodoTaskEntity, Long> {
    List<TodoTaskEntity> findByDueDateBeforeAndStateNot(LocalDate currentDate, TodoTaskState todoTaskState);
}
