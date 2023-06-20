package org.campusmolndal.todo;

import org.campusmolndal.interfaces.TodoDao;

import java.util.List;

public class TodoService {
    TodoDao todoDao;
    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }
    public Todo delete(Todo todo) {
        if(todo == null) return null;
        return todoDao.delete(todo.getId());
    }
    public Todo create(Todo todo) {
        if(todo == null) return null;
        return todoDao.create(todo);
    }
    public List<Todo> getByUserId(String id) {
        return todoDao.getByUserId(id);
    }

    public List<Todo> listTodos() {
        return todoDao.list();
    }

    public Todo update(Todo todo) {
        if(todo == null) return null;
        return todoDao.update(todo.getId(), todo);
    }
}
