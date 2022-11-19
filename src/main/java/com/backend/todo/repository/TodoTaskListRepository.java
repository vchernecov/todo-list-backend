package com.backend.todo.repository;

import com.backend.todo.entity.TodoTaskListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoTaskListRepository extends JpaRepository<TodoTaskListEntity, Long> {
    TodoTaskListEntity findByUserId(long userId);
}
