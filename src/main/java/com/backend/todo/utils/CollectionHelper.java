package com.backend.todo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CollectionHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionHelper.class);
    public static<T> Set<T> merge(Set<T> newSet, Set<T> oldSet) {
        LOGGER.info("Set: {} and Set: {} is merged", newSet, oldSet);
        return Stream.concat(newSet.stream(), oldSet.stream()).collect(Collectors.toSet());
    }
}
