package org.campusmolndal.todo;

import org.campusmolndal.interfaces.Repository;

import java.util.List;

public class Todo {
    Repository<TodoDao> repository;
    public Todo(Repository<TodoDao> repository) {
        this.repository = repository;
    }
    public void create(TodoDao todoDao) {
        repository.create(todoDao);
    }
    public TodoDao read(String id) {
        return repository.read(id);
    }
    public List<TodoDao> list() {
        return repository.list();
    }
    public void update(TodoDao todoDao) {
        repository.update(todoDao);
    }
    public void delete(TodoDao todoDao) {
        repository.delete(todoDao);
    }
}
