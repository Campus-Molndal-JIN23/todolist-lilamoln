package org.campusmolndal.mongodb;

import org.campusmolndal.interfaces.TodoDao;
import org.campusmolndal.todo.Todo;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoTodoDao implements TodoDao {
    private final MongoFacade mongoFacade;
    public MongoTodoDao(MongoFacade mongoFacade) {
        this.mongoFacade = mongoFacade;
    }

    @Override
    public Todo create(Todo todo) {
        if(todo == null) return null;
        return Todo.fromDocument(mongoFacade.insert(todo.toDocument()));
    }

    @Override
    public Todo read(String id) {
        return Todo.fromDocument(mongoFacade.get(id));
    }

    @Override
    public Todo update(Todo todo) {
        if(todo == null) return null;
        return Todo.fromDocument(mongoFacade.update(todo.toDocument()));
    }

    @Override
    public Todo delete(Todo todo) {
        if(todo == null) return null;
        if(todo.getId() == null) return null;
        return Todo.fromDocument(mongoFacade.delete(todo.toDocument()));
    }

    @Override
    public List<Todo> list() {
        List<Document> result = mongoFacade.list();
        if(result == null) return null;
        List<Todo> todos = new ArrayList<>();
        for (Document document : result) {
            todos.add(Todo.fromDocument(document));
        }
        return todos;
    }

    @Override
    public List<Todo> getByUserId(String id) {
        List<Document> result = mongoFacade.findTodoByUserId(id);
        if(result == null) return null;
        List<Todo> todos = new ArrayList<>();
        for (Document document : result) {
            todos.add(Todo.fromDocument(document));
        }
        return todos;
    }
}
