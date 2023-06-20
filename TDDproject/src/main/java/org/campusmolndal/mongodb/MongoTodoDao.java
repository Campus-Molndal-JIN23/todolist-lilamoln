package org.campusmolndal.mongodb;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.campusmolndal.interfaces.TodoDao;
import org.campusmolndal.todo.Todo;

import java.util.ArrayList;
import java.util.List;

public class MongoTodoDao extends MongoDao<Todo> implements TodoDao {
    public MongoTodoDao() {
        super("todos", Todo.class);
    }

    @Override
    public List<Todo> getByUserId(String id) {
        List<Todo> result = new ArrayList<>();
        for(Todo todo : getCollection().find(new Document("assigned_to",new ObjectId(id)))) {
            result.add(todo);
        }
        return result;
    }
}