package org.campusmolndal.todo;

import org.bson.BsonType;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.bson.types.ObjectId;

public class Todo {
    @BsonId()
    @BsonRepresentation(BsonType.OBJECT_ID)
    @BsonProperty("_id")
    String id;
    @BsonProperty("text")
    String text;
    @BsonProperty("done")
    boolean done;

    public boolean isDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @BsonId()
    @BsonRepresentation(BsonType.OBJECT_ID)
    @BsonProperty("assigned_to")
    String user;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public Document toDocument() {
        Document document = new Document();
        if(this.id != null) document.append("_id", new ObjectId(this.id));
        document.append("text", this.text);
        document.append("done", this.done);
        document.append("assigned_to", this.user);
        return document;
    }
    public static Todo fromDocument(Document document) {
        if(document == null) return null;
        Todo todo = new Todo();
        todo.setId(document.getObjectId("_id").toString());
        todo.setText(document.getString("text"));
        todo.setDone(document.getBoolean("done"));
        todo.setUser(document.getString("assigned_to"));
        return todo;
    }
}