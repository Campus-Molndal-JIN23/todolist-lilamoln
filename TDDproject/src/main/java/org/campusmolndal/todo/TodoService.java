package org.campusmolndal.todo;

import org.campusmolndal.interfaces.TodoDao;

import java.util.List;

public class TodoService {
    TodoDao todoDao;
    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }
    public Todo changeStatus(Todo todo, boolean status) {
        todo.setDone(status);
        return update(todo);
    }
    public Todo changeText(Todo todo, String text) {
        todo.setText(text);
        return update(todo);
    }
    public Todo delete(Todo todo) {
        return todoDao.delete(todo.getId());
    }
    public Todo create(Todo todo) {
        return todoDao.create(todo);
    }
    public List<Todo> getByUserId(String id) {
        return todoDao.getByUserId(id);
    }

    public List<Todo> listTodos() {
        return todoDao.list();
    }

    public Todo update(Todo todo) {
        return todoDao.update(todo.getId(), todo);
    }
}
