package org.campusmolndal.sqlite;

import org.campusmolndal.interfaces.TodoDao;
import org.campusmolndal.todo.Todo;

import java.util.List;

public class SQLiteTodoDao implements TodoDao {
    private final SQLiteHandler sqLiteHandler;
    public SQLiteTodoDao(SQLiteHandler sqLiteHandler) {
        this.sqLiteHandler = sqLiteHandler;
    }
    @Override
    public Todo create(Todo todo) {
        return sqLiteHandler.insert(todo);
    }

    @Override
    public Todo read(String id) {
        return sqLiteHandler.getTodo(id);
    }

    @Override
    public Todo update(Todo todo) {
        return sqLiteHandler.update(todo);
    }

    @Override
    public Todo delete(Todo todo) {
        return sqLiteHandler.delete(todo);
    }

    @Override
    public List<Todo> list() {
        return sqLiteHandler.listTodos();
    }

    @Override
    public List<Todo> getByUserId(String id) {
        return sqLiteHandler.getTodosByUserId(id);
    }
}
