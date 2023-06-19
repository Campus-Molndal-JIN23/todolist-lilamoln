package org.campusmolndal.todo;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

public class TodoDao {
    @BsonId
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

    @BsonId
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
}