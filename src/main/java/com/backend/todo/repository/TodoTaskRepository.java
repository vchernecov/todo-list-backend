package com.backend.todo.repository;

import com.backend.todo.entity.TodoTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoTaskRepository extends JpaRepository<TodoTaskEntity, Long> {
}
