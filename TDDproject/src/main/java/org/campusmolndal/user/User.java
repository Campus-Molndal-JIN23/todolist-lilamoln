package org.campusmolndal.user;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.campusmolndal.todo.Todo;

import java.util.List;

public class User {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    String id;
    @BsonProperty("name")
    String name;
    @BsonIgnore
    List<Todo> todos;
    public User() {

    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Todo> getTodos() {
        return this.todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
