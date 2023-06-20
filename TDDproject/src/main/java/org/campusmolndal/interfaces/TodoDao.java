package org.campusmolndal.interfaces;

import org.campusmolndal.todo.Todo;

import java.util.List;

public interface TodoDao extends Dao<Todo> {
    List<Todo> getByUserId(String id);
}