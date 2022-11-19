package com.backend.todo.mapper;

public interface Mapper<T, S> {
    T map(S source);
}
