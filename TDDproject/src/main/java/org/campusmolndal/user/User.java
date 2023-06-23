package org.campusmolndal.user;

import org.bson.BsonType;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.bson.types.ObjectId;
import org.campusmolndal.todo.Todo;

import java.util.ArrayList;
import java.util.List;

public class User {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    @BsonProperty("_id")
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
        if(this.todos == null) return new ArrayList<>();
        return this.todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
    public Document toDocument() {
        Document document = new Document();
        if(this.id != null) document.append("_id", new ObjectId(this.id));
        document.append("name", this.name);
        return document;
    }
    public static User fromDocument(Document document) {
        User user = new User();
        user.setId(document.getObjectId("_id").toString());
        user.setName(document.getString("name"));
        return user;
    }
}
