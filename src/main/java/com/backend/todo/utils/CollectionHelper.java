package com.backend.todo.utils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CollectionHelper {
    public static<T> Set<T> merge(Set<T> newSet, Set<T> oldSet) {
        return Stream.concat(newSet.stream(), oldSet.stream()).collect(Collectors.toSet());
    }
}
